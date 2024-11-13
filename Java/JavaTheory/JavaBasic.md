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


    
### EscapeCharacter

| Escape Sequence | 설명                               | 예제 출력                                       |
|------------------|------------------------------------|-------------------------------------------------|
| `\n`             | 줄바꿈 (New Line)                 | `"Hello\nWorld"` → <br>Hello<br>World           |
| `\t`             | 탭 (Tab)                          | `"Hello\tWorld"` → Hello    World               |
| `\\`             | 백슬래시 (`\`) 출력                | `"C:\\Users\\Name"` → C:\Users\Name             |
| `\"`             | 큰따옴표 (`"`) 출력                | `"She said, \"Hello!\""` → She said, "Hello!"   |
| `\'`             | 작은따옴표 (`'`) 출력              | `'It\'s a book'` → It's a book                  |
| `\r`             | 캐리지 리턴 (Carriage Return)     | OS에 따라 줄바꿈으로 작동 (Windows)             |
| `\b`             | 백스페이스 (Backspace)            | `"abc\b"` → ab                                  |

### 형 변환(Type Casting)

- 묵시적 형 변환
    - 작은 자료형을 큰 자료형으로 변환할때 자동으로 변환되는 것
        - 데이터 손실이 없기에 자동으로 변환되어도 괜찮음
- 명시적 형 변환
    - 큰 자료형에서 작은 자료형으로 변환할때 캐스팅 연산자를 통해 변환시키는 것
        - 데이터 손실이 있을수 있기에 수동으로 변환되어야 함
    - 값이 자료형의 범위를 초과하면 데이터가 왜곡될 수 있음