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
### 변수 선언 & 정의

- Type 변수명 = 값;
    - 선언
        - Type 변수명
    - 정의
        - 변수명 = 값;

### 데이터 타입

- var ⚠️사용에 주의가 필요함, 사용을 권장하지는 않는거 같음
    - var 변수명 = 값
        - 가독성을 일부 개선할 수 있음
            - ex) Map<String, List<Integer>> map = new HashMap<>();
            - → var map = new HashMap();
        - 하지만 많은 경우에서 가독성을 해침
            - ex) var data = getData();
                - 타입을 알 수 없음
- boolean
    - true / false
- 정수형
    - byte, char, short, int, long
- 실수형
    - float, double

### 비교연산자

- ==, !=, <, <=, >, >=

### 변환 명령어

- 변경하고싶은 타입.parseType(String)
    - ex) int num = Integer.parseInt(”123”);
    - ex) double num = Double.parseDouble(”123.123”);
    - ex) Long num = Long.parseLong(”10000000000”);

### 수학 함수

- **Math.abs(int, double)**
    - 절대값을 반환
- **Math.ceil(double)**
    - 올림
- **Math.floor(double)**
    - 내림
- **Math.round(double)**
    - 반올림
- **Math.pow(double a,double b)**
    - $a^b$
- **Math.sqrt(double)**
    - 제곱근을 반환
- **Math.max(int, int  or  double, double)**
    - 최대값 반환
    - int double 혼합 사용 불가
- **Math.min(int, int or double, double)**
    - 최소값 반환
    - int double 혼합 사용 불가
- **Math.random()**
    - 난수 생성
- **Math.PI**
    - $\pi$

### 조건문

- if문
    
    ```java
    if(조건문){
    	body;
    }
    ```
    
- if else 문
    
    ```java
    if(조건문){
    	body;
    else{
    	body;
    }
    ```
    
- else if 문
    
    ```java
    if(조건문){
    	body;
    else if(조건문){
    	body;
    else{
    	body;
    }
    ```
    

### 반복문

- for문
    
    ```java
    for(초기화; 조건식; 증감식){
    	for문 body;
    }
    ```
    
- while문
    
    ```java
    while(조건식){
    	body;
    	//조건식을 종료시킬 수 있게 구성되어야 함
    }
    ```
    
- do-while문
    - 무조건 한번은 수행하게 되는 반복문
    
    ```java
    do{
     body;
    }while(조건식);
    ```