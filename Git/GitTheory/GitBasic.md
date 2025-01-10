## Git

- 파일의 버젼을 관리하는 시스템

✅ **사용목적**

- 소스코드의 버젼 관리 및 공유
  - 소스코드의 각 버젼을 commit 단위로 기록
- 데이터 원격 관리
- Local Repository 소스코드 버젼 관리
  - Local == 개인 컴퓨터
- Remote Repository 소스코드 버젼 관리
  - Remote == 서버

✅ **설치방법**

- 윈도우
  - https://git-scm.com/download/win
- mac
  1. 터미널 접속
  2. brew install git
     - brew가 설치되지 않은 경우
       1. https://brew.sh/ 접속
       2. /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
          1. 위 명령어를 터미널에 입력
       3. mac 비밀번호 입력
       4. echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> /Users/사용자이름/.zprofile
       5. eval "$(/opt/homebrew/bin/brew shellenv)"
          1. 위 명령어 2개를 터미널에 입력
       6. brew —version 명령어를 통해 설치 확인

✅ **Git 사용자 설정**

- 누가 어떤 작업을 수행했는지 추적하기 위함
- global 설정
  - git config —global [user.name](http://user.name) “git 계정명”
  - git config —global [user.email](http://user.email) “git email”
- local 설정
  - 특정 프로젝트, 저장소에서만 다른 사용자 정보를 사용하고 싶을때 사용
    - ex) 팀 / 개인 프로젝트
    - git config [user.name](http://user.name) “git 계정명”
    - git config [user.email](http://user.email) “git email”

✅ Git 변수 조회

- global 변수 조회
  - global로 설정된 name, email등 확인 가능
  - git config —global —list
- local 변수 조회
  - local로 설정된 name, email등 확인 가능
  - git config —local —list

## 원격 저장소

**✅ 원격 저장소 등록**

- git remote add origin [원격 저장소 주소]
  - 원격 저장소 주소 == 작업물을 업로드할 github주소

**✅  원격 저장소 복제**

- git clone [원격 저장소 주소] [원하는 폴더명(공백가능)]
  - github에서 clone하고 싶은 프로젝트 선택 → Code → HTTPS url 복사
  - clone시 origin(원격 저장소) 자동 등록 되기에 추가 등록 불필요
    - git remote -v 로 확인 가능
- 같은 저장소를 여러 폴더로 생성 가능
  - git clone [원격 저장소 주소] NameA
  - git clone [원격 저장소 주소] NameB

**✅  원격 저장소 삭제**

- origin에 등록된 url을 삭제
  - 실제 github에는 영향 X
- git remote remove origin

## Git 버젼 관리

- git 프로세스
  - git clone → 작업 → git add → git commit → git pull → git merge → 충돌 해결 → git push
- **git add**
  - 파일, 디렉토리를 버젼 관리 대상으로 지정
  - stage에 올려놓음
  - 단일 파일 지정
    - git add [파일명]
  - 폴더 지정
    - git add .
      - 모든 변경사항 추가
        - ex) 생성, 수정, 삭제
      - 하위 디렉토리까지 추가
    - git add \*
      - 생성, 수정된 파일만 추가
      - 하위 디렉토리 제외
- **git commit -m “커밋 내용”**
  - 소스 파일의 변경 사항을 기록
- **git commit —allow-empty -m “커밋 내용”**
  - 빈 commit
    - 단순 이력만 남기기 위함
- **git pull <원격 저장소 url> <branch>**
  - 원격지(Github에 올라와있는 코드)를 로컬(내 컴퓨터)에 반영
    - 원격저장소와 내 코드를 동기화 시키기 위함
  - pull = fetch + merge
- **git merge <병합할 branch>**
  - <병합할 branch>의 내용을 현재 branch에 병합
- **git push <원격 저장소 url> <branch>**
  - 로컬(내 컴퓨터) 코드를 원격지(GitHub)에 반영
    - 작업물을 반영하기 위함
  - git 자격증명
    - push 시에 id와 비밀번호 요청
    - git config —global credential.helper store 명령어 사용시 더이상 입력하지 않아도 됨

## 버젼 충돌

- 내가 작업한 local 코드와 github에 올라간 코드 간 충돌이 발생
  - push 작업 중에 발생
  - 같은 파일의 같은 줄을 변경하는 경우 주로 발생
- 해결방법
  - git pull이후 충돌난 부분을 수동으로 수정
    - 충돌 수정 예시
      - <<<<<<<HEAD
      - [나의 작업물]
      - =======
      - [githhub에 올라간 코드]
      - > > > > > > > branch 이름
  - 수정 후 add → commit → push

## Git tag

- Lightweight Tag
  - 특정 커밋에 대한 포인터 역할
- Annotated Tag
  - 주석이 달린 tag
  - tag명, 이메일, 날짜, 메시지
- **git tag <tag name>**
  - 최종 커밋에 태그 생성
  - Lightweight Tag
- **git tag -d <tag name>**
  - 태그 삭제
- **git push origin —delete <tag name>**
  - 원격 저장소 태그 삭제
- **git push origin <tag name>**
  - 단일 태그 푸쉬
- **git push origin —tags**
  - 모든 태그 푸쉬
- **git tag -a <tag_name> -m “메시지”**
  - 최종 커밋에 메시지, 태그 생성
  - Annotated Tag
- **git tag -a <tag name> <commit id> -m “메시지”**
  - 특정 commit에 tag생성
  - Annotated Tag
- **git tag -a <tag name> <branch name> -m “메시지”**
  - 특정 branch의 최종 commit에 tag 생성
  - Annotated Tag
- **git checkout -b <branch name> <tag name>**
  - 특정 tag로 이동

## Git 명령어들

✅ **commit 수정**

- **git commit —amend -m “커밋 내용”**
  - 최신 commit으로 덮어 쓰기
  - 메시지를 수정하기 위함
  - 스테이징된 파일이 있다면 커밋에 포함됨
  - 히스토리에 있던 기존 커밋은 사라지고 해당 커밋으로 대체됨
- **git commit —amend -no-edit**
  - 최신 commit으로 덮어 쓰기
  - 메시지를 수정하지 않고 스테이징된 파일을 커밋
- **git log**
  - commit 이력 조회

✅ **git 작업 취소**

- **git restore <file_name>**
  - 작업 디렉터리에서 작업한 내용을 취소
    - 파일을 마지막 커밋 상태로 복구
  - 파일이 스테이징되지 않아야 동작 가능
- **git restore —source <commit id><file_name>**
  - 작업 디렉터리를 특정 커밋의 파일 상태로 복구
  - 과거 커밋을 작업하고 싶을때 사용
- **git restore —staged <file_name>**
  - 스테이징 영역에서 파일을 제거하고 작업 디렉터리로 이동시킴

**✅ git 작업 삭제**

- **git rm —cached <file_name>**
  - 스테이징 영역에서만 파일을 제거
    - == local repository에서만 파일을 제거
  - 작업 디렉터리에는 파일이 유지됨
    - == local directory에서는 유지
- **git rm <file_name>**
  - 로컬 디렉터리, 로컬 레포에서 파일을 제거
- **git rm -f <file_name>**
  - 파일을 강제로 제거
- **git mv <file_name> <new_file_name>**
  - 파일, 디렉터리를 이동하거나 이름 변경

## git rebase

- 커밋 히스토리를 깔끔하게 유지하고 싶을 때 사용
- 공유된 브랜치에서 rebase시 커밋 해시가 변경되기에 사용하지 않음
- merge

  ```java
  A---B---C  (main)
       \
        D---E  (feature)

  merge 이전 -> merge 이후

  A---B---C---M  (main)
       \     /
        D---E
  ```

- rebase

  ```java
  A---B---C  (main)
       \
        D---E  (feature)

  rebase 이전 -> rebase 이후

  A---B---C---D---E (feature)
  ```

- **git rebase <base branch>**
  - 현재 branch의 변경사항을 base branch에 합병

## **git branch**

- **git branch**
  - branch 목록 나열
- **git branch <branch name>**
  - branch 생성
- **git branch -d <branch name>**
  - 삭제
- **git branch -m <new branch name>**
  - 이름변경
- git checkout → git switch + git restore
- **git switch <branch name>**
  - branch 이동
- **git switch -c <new branch name>**
  - branch를 생성하면서 이동
- **git restore**
  - 특정 파일을 이전 커밋, 상태로 복구
- **git stash**
  - branch 이동 전 저장하지 않은 변경 사항을 임시 저장
  - 작업 내용을 커밋하지 않으면 브랜치 변경이 불가능하기에 사용
- **git stash apply**
  - 가장 최근에 임시 저장한 변경 사항을 복구
- **git stash pop**
  - 가장 최근에 임시 저장한 변경 사항을 복구하면서 스택에서 제거

## git branch 전략

- main : 서비스 배포용, 중심 branch
- feature : 기능별 branch
- develop : feature의 부모 branch
- release : QA를 위한 branch
- hotfix : 버그 fix용 branch

## Reset & Revert

✅ **Reset**

- 특정 커밋으로 되돌리는 명령어
- 원격 공유가 된 경우 권장하지 않음
- **git reset —hard [commit-ref]**
  - 작업했던 작업물과 커밋은 영구히 제거됨
  - HEAD : 가장 최근에 수행한 커밋
- **git reset —soft [commit-ref]**
  - 커밋은 제거되지만 작업했던 작업물과 add기록은 남아있음

✅ **Revert**

- 특정 커밋으로 되돌리는 명령어
  - 커밋 기록은 그대로 남긴채 특정 커밋 위치로 이동 가능
- **git revert HEAD -m “메세지”**
- **git revert <commit id> —edit**
  - 메세지를 포함한 새로운 commit 생성
- **git revert <commit id> —no-edit**
  - 메세지 없이 새로운 commit 생성
- ex)
  - **git reset —hard**
    - a → b → c → d(head)
    - git reset —hard HEAD
    - a → b → c
      - d의 커밋, 작업물 영구히 삭제
  - **git reset —soft**
    - a → b → c → d(head)
    - git reset —soft HEAD
    - a → b → c
      - d의 커밋 삭제, 작업물 유지
  - **git revert**
    - a → b → c → d
    - git revert b
    - a → b → c → d → e(b’)
      - e의 커밋 메세지 : b 수정

## fork

- 다른 사용자의 저장소를 복사하여 내 Github 저장소에 가져오는 것
- 방법
  1. 원하는 github 저장소에서 fork 클릭
  2. git clone [내 저장소 HTTPS URL] 명령어를 통해 로컬 환경에 코드를 다운로드
  3. git remote add upstream [fork한 github의 HTTPS URL]
     1. 지우기 : git remote remove upstream
  4. git pull upstream main
  5. git push origin main

## Pull Request

- fork하여 작업한 결과물을 원저장소에 추가하기 위해 요청을 하는 것
  - 프로젝트에 컨트리뷰션을 제출한다 == 코드, 문서, 버그 수정등의 기여를 프로젝트에 추가해 달라고 요청

**✅ 절차**

1. 원 저장소에서 fork 수행
2. git clone [나의 github HTTPS URL]
3. git remote add upstream [원본 github HTTPS URL]
4. git remote add origin [나의 github HTTPS URL]
5. 작업 수행
6. git add .
7. git commit -m [커밋 메세지]
8. git pull upstream main

   - branch 사용시
     - git checkout main
     - git pull upstream main
     - git checkout branch
     - git merge main

9. git push origin main

- 내 저장소에 push한 후 원본 저장소에 pull request 수행
- branch를 만들고 작업하는 경우 로컬의 main 브랜치와 병합이 필요하기에 merge 수행
- branch를 만들지 않고 main에 작업하는 경우 pull로 main브랜치를 최신화 하면 되기에 merge가 필요 없음
- git push upstream main
  - push 권한이 없는 경우가 많기 때문에 불가능

1. 나의 github 저장소에서 compare & pull request 클릭 → pull request
2. contribute → pull request
