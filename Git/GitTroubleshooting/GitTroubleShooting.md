### git 자격증명 문제

- mac
    
    ```bash
    git config --global credential.helper osxkeychain
    ```
    
- key chain 접근 → 로그인 → 새로만들기
    - 키체인 항목 이름 : https://github.com
    - 계정이름 : github 사용자 이름
    - 암호 : 토큰
        - 토큰 생성법
            - github 이동 → setting → Developer settings → personal access token → generate new token
- 윈도우
    - 자격증명관리자 → Windows 자격증명 → 일반 자격 증명 추가
        - 키체인 항목 이름 : https://github.com
        - 계정이름 : github 사용자 이름
        - 암호 : 토큰
            - 토큰 생성법
                - github 이동 → setting → Developer settings → personal access token → generate new token

### Readme로 인한 push reject문제

1. 로킬 레포지토리 Readme 파일 존재 할때
    - 깃헙 저장소 생성 시 Readme없이 생성
2. 로컬 레포지토리 Readme 존재 X, 깃헙에서 Readme 생성하고자 할때
    - git clone으로 원격 저장소 복제하여 사용
3. 로컬 레포지토리와 깃헙 레포지토리에 Readme가 존재할때
    - 강제 push
        - git push -f origin main

### 빈 커밋

- git commit —allow-empty -m “커밋 내용”은 파일 변경이 없기에 github history에는 표시 되지만 Github 파일 옆에는 표시 안됨