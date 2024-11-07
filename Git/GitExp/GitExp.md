(Git) Git 계정을 생성하고 로컬 저장소에 간단한 텍스트 파일 커밋하기

- Git 웹사이트(예: GitHub, GitLab 등)에서 계정을 생성합니다. 로컬 컴퓨터에 Git 저장소를 생성합니다. 텍스트 편집기를 사용하여 간단한 텍스트 파일을 생성합니다. Git 명령어(예: git add, git commit)를 사용하여 텍스트 파일을 저장소에 커밋합니다. 커밋 과정의 스크린샷을 찍어 결과물로 제출합니다.

```bash
1. mkdir gitPractice
2. cd gitPractice
3. git init
4. 텍스트 파일 생성
5. git add hello.txt
6. git commit -m "Add hello.txt"
```
---

(Git) 로컬 저장소에서 새로운 브랜치를 생성하고 파일을 추가한 뒤 메인 브랜치와 병합하기

- Git 저장소에서 새로운 브랜치를 생성합니다. 새로운 브랜치에서 텍스트 파일을 추가합니다. Git 명령어(예: git checkout, git merge)를 사용하여 새로운 브랜치를 메인 브랜치와 병합합니다. 브랜치 생성, 파일 추가, 병합 과정의 스크린샷을 찍어 결과물로 제출합니다.

```bash
1. git branch newBranch
2. git checkout newBranch
3. 텍스트파일 생성
4. git add gitBranch.txt
5. git commit -m "Add gitBranch.txt"
6. git checkout main(master)
7. git merge newBranch 
```
---
(Java) GitHub 계정 만들고 간단한 텍스트 파일 Push하기

- GitHub 웹사이트에서 계정을 생성합니다. 로컬 컴퓨터에 Git 저장소를 생성합니다. 텍스트 편집기를 사용하여 간단한 텍스트 파일을 생성합니다. Git 명령어(예: git push)를 사용하여 텍스트 파일을 GitHub 저장소에 Push합니다. GitHub 계정 생성 및 파일 Push 과정의 스크린샷을 찍어 결과물로 제출합니다.
```bash
1. git init
2. git add .
3. git commit -m "Java Simple Program"
4. git remote add origin url
5. git pull origin main
6. git push origin main
```