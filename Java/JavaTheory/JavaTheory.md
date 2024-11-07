### 자바 장점

- 객체지향(Object Orientation)
- 범용성(Universality)
- 자동화(Automation)
- 대중성(Popularity)

### Edition

- Standard Edition
    - 데스크탑 환경
        - java의 핵심 라이브러리
            - ex) java.lang, java.util, java.io
        - JVM
        - 기본적인 API
- Enterprise Edition
    - 서버환경
        - 웹 서버 구축에 필요한 API, 프레임워크 포함
            - ex) Servelt, JSP, EJB, JPA
        - 웹 서버 환경 지원
            - ex) Tomcat
        - 멀티쓰레딩, 트랜잭션 관리, DB연결 기능등을 제공
- Android
    - 모바일 환경
        - Android SDK 제공

### Eclipse 설치

- http://eclipse.org
- jdk 설치
    - 윈도우
        - google에 jdk검색해서 다운로드
    - 맥
        - brew install openjdk
            - 가장 최신 LTS jdk 설치
- jdk 적용
    - 윈도우
        - preference → java → installedJREs → Add → StandardVM
            - JRE home : C:\Program Files\Java\jdk
    - 맥
        - Eclipse → Setting → java → installedJREs → Add → StandardVM
            - JRE home : /Users/ahnhyeongjun/Library/Java/JavaVirtualMachines/corretto-17.0.13/Contents/Home