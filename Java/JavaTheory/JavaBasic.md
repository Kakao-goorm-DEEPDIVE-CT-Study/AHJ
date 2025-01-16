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

### 재귀함수

- 자기 자신을 호출하는 함수
    - 반복적으로 쪼갤 수 있는 문제를 해결할 때 사용
- 기저조건
    - 재귀호출을 멈추는 조건
    - 무한 호출로 인해 StackOverflow가 발생할 수 있기에 반드시 포함되어야 함
- 예제
    
    ```java
    public class Fibonacci {
        public static int fibonacci(int n) {
            // 기저 조건
            if (n == 0) return 0;
            if (n == 1) return 1;
            // 재귀 호출
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
        
        public static void main(String[] args) {
            System.out.println(fibonacci(5)); // 출력: 5
        }
    }
    
    ```
    

### 문자열,정수,실수 포맷 지정자

- String.format()혹은 System.out.printf()를 사용
    

    | 구분     | 지정자 | 설명                                |
    |----------|--------|-------------------------------------|
    | 문자열   | `%s`   | 문자열 출력                         |
    | 문자     | `%c`   | 문자 출력 (Unicode character)       |
    | 정수     | `%d`   | 10진수 정수 출력                    |
    | 16진수   | `%x`   | 16진수 정수 출력                    |
    | 8진수    | `%o`   | 8진수 정수 출력                     |
    | 실수     | `%f`   | 소수점 포함 실수 출력               |

- 예제
    
    ```java
    public class StringFormatting {
        public static void main(String[] args) {
            String name = "Alice";
            int age = 25;
            double score = 92.5;
    
            System.out.printf("이름: %s, 나이: %d, 점수: %.1f\n", name, age, score);
        }
    }
    -> 이름: Alice, 나이: 25, 점수: 92.5
    ```
    

### 날짜 포맷 지정자

| 구분     | 지정자 | 설명                                |
|----------|--------|-------------------------------------|
| 요일     | `%tA`  | 요일 출력 (예: Monday, 화요일)       |
| 월 이름  | `%tB`  | 월 이름 출력 (예: January, 11월)    |
| 연도     | `%tY`  | 4자리 연도 출력 (예: 2024)          |
| 월       | `%tm`  | 월 (숫자 형식, 예: 11)             |
| 일       | `%td`  | 일 (숫자 형식, 예: 13)             |
| 시간     | `%tH`  | 시간 (24시간제, 예: 14)            |
| 분       | `%tM`  | 분 (예: 45)                        |
| 초       | `%tS`  | 초 (예: 30)                        |

- 예제
    
    ```java
    import java.util.Date;
    
    public class DateFormatting {
        public static void main(String[] args) {
            Date now = new Date();
            System.out.printf("오늘은 %tY년 %tm월 %td일 %tA입니다.\n", now, now, now, now);
            System.out.printf("현재 시간: %tH시 %tM분 %tS초\n", now, now, now);
        }
    }
    -> 오늘은 2024년 11월 13일 화입니다.
    	 현재 시간 : 15시 45분 00초
    ```

### pacakge와 classpath 중요성

- package
    - 관련 클래스들을 논리적으로 그룹화 가능
        - ex) com.myapp.controller / com.myapp.service / com.myapp.model
    - package로 인해 고유한 네임스페이스를 얻게 됨
        - ex) com.game.User / com.app.User
- classpath
    - 외부 라이브러리 사용시 JAR파일 경로를 제대로 추가해야 사용 가능
        - IDE에서 자동으로 추가
    - 협업시 외부 라이브러리 의존성이 누락되면 빌드,실행 오류 발생
        - maven, gradle로 관리 가능
            - IDE에서 자동으로 관리

## JIT Compiler

- 프로그램 실행 중에 바이트코드를 기계어로 변환하는 컴파일러
- 주요 사용 사례 : Java의 JVM, .NET의 CLR, JS의 V8엔진

**✅ 작동방식**

- 소스코드 작성
    - Java, c# 등
- 바이트코드 생성
    - 컴파일러에 의해 소스코드가 바이트 코드로 변환
    - 바이트 코드는 플랫폼에 독립적이고 JVM, .NET에서 실행됨
- JIT 컴파일
    - 프로그램이 실행되면 JIT컴파일러가 바이트코드를 기계어로 번역
    - CPU에서 직접 실행
- 캐싱, 최적화
    - 번역된 기계어는 캐싱되어 재사용
    - 반복적으로 수행되는 코드는 빠른 성능을 보임
    - 처음 시작하는 코드는 변환 단계에 의해 성능이 저하됨

**✅ 최적화 기법**

- **Hotspot Optimization**
    - 자주 호출, 반복 호출되는 코드를 감지하여 최적화
- **Inline Expansion**
    - 작은 함수 호출시 실제 호출 위치에 함수 코드를 복사하여 호출 오버헤드를 줄임
- **Dead Code Elimination**
    - 사용되지 않는 코드를 제거하여 속도를 높임
- **Loop Unrolling**
    - 루프를 펼쳐서 반복 횟수를 줄이고 성능을 향상시킴
- **Garbage Collection Integration**
    - 메모리를 밀접하게 사용하여 메모리를 최적화

## JVM

- 자바 프로그램이 플랫폼 독립적으로 실행될 수 있도록 바이트코드를 읽고 실행하는 가상 머신

**✅ 역할**

- 바이트코드 실행
- 메모리 관리
    - 힙, 스택 영역을 포함한 메모리 영역을 관리
    - 객체 생성 소멸 관리
- Garbage Collection
    - 더이상 사용되지 않는 객체를 자동으로 제거하여 메모리를 효율적으로 사용
- 플랫폼 독립성 제공
    - 운영체제, 하드웨어에 의존하지 않음
- 동적로딩
    - 프로그램 실행 중 필요한 클래스 파일 로드하여 의존성을 동적으로 해결

**✅ 구성요소**

- **Class Loader**
    - 바이트코드(.class 파일)을 메모리에 로드
    - 역할
        - **클래스 로드**
            - 읽어들인 바이트코드를 Method Area에 적재
        - **링크**
            - 클래스 구조, 참조를 확인해 필요한 의존성 해결
                - 외부 라이브러리, 프레임워크, API
                    - ex) Gradle, Maven
                - 내부 코드 의존성
                    - 클래스간 의존
                - 런타임 의존성
                    - 실행 시점에 필요한 모듈, 라이브러리
                - 컴파일타임 의존성
                    - 컴파일시에 필요한 라이브러리
        - **초기화**
            - 정적 필드에 값을 초기화
- **Execution Engine**
    - 바이트코드를 기계어로 변환하고 실행
    - 세부구성
        - 인터프리터
        - JIT Compiler
- **메모리 구조**
    - 실행 중인 프로그램의 데이터를 저장하는 공간
    - **Method Area**
        - 클래스 정보, 메소드, 상수 풀 저장
    - **Heap**
        - 객체, 배열 저장
        - Garbage Collection이 수행
    - **Stack**
        - 지역 변수, 메소드 호출 정보 저장
    - **PC Register**
        - 현재 실행 중인 JVM 명령어의 주소 저장
    - **Native Method Stack**
        - 네이티브 코드 실행에 사용
- **Garbage Collector(GC)**
- **Java Native Interface(JNI)**
    - 자바 프로그램에서 네이티브 코드(C, C++)를 호출

**✅ 작동방식**

- 소스코드 작성
- 컴파일
    - .java → .class
- 클래스 로드
    - Class Loader
- 바이트코드 실행
    - Execution Engine
- Garbage Collection