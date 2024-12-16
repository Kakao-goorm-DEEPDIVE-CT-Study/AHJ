## 1. 리팩토링

- 외부 기능 변화 없이 내부적으로 효율적 시스템 재구성
- **목표**:
    - 관심사에 맞게 프로그램 효율적으로 구성
    - 스프링 원칙에 부합하는 어플리케이션 구조 구성 가능

---

## 2. 관심사와 제어권

**관심사**

- 해당 코드가 집중하고 있는 역할
- **관심사 분리 :** 하나의 모듈(클래스, 함수)는 하나의 관심사만 가져야 한다
- **관심사** **파악** : 각 객체는 본연의 역할에만 집중하도록 분리 필요

**객체의 제어권**

- 객체가 다른 객체를 생성, 관리하는 권한
- **문제점**: 의존성이 강해지고 유지보수 어려움

### **제어의 역전 (IoC)**

- 객체의 제어권을 상위 객체로 이전
    - 객체를 생성, 관리하는 주체가 프로그래머 → 스프링 프레임워크
- Spring에서 제공하는 모든 기능의 기초가 되는 기반 기술
- 각 객체는 자신이 사용할 객체를 선택, 생성하지 않음
    
    ```java
    public class UserService {
        private EmailService emailService = new EmailService();
    
        public void registerUser(String username) {
            emailService.send("Welcome, " + username);
        }
    }
    
    -> 
    
    public class UserService {
        private EmailService emailService;
    
        public UserService(EmailService emailService) { // 외부에서 주입
            this.emailService = emailService;
        }
    
        public void registerUser(String username) {
            emailService.send("Welcome, " + username);
        }
    }
    
    ```
    
- **필요성**:
    - 객체는 본연의 역할에만 집중
    - 객체 생성,선택 책임 분리
- **장점**:
    - 코드 유연성 및 확장성 증가

---

## 3. Spring의 IoC

### Object Factory

- **Bean** : Spring에서 제어권을 가지고 직접 생성, 관리하는 객체
    - org.springframework.beans.factory.BeanFactory를 이용해 객체 생성, 관리

### **Application Context :**

- IoC개념을 적용해서 관리할 모든 객체에 대한 제어권 담당
    - 모든 객체를 생성후 보관
    - == IoC 컨테이너
- BeanFactory를 상속한 org.springframework.context.ApplicationContext
- XML로 등록 가능(자주 사용 X)
    
    ```xml
    <bean id="emailServiceClient" class="com.spring.mvcproject.service.EmailServiceClient" />
    ```
    
- Annotation으로 자동 등록 가능(자주 사용 O)
    - Annotation 기반으로 동작하기에 각 Bean을 수동으로 등록해야하는 빈도 감소
    - @Controller, @Service, @Component
- 장점
    - 본연의 기능,관심사에 집중 가능
    - Bean 관리 자동화
        - 각 Bean은 Spring 컨테이너 내에 한개의 객체로 생성
            - Singleton Registry로 관리
            - 강제로 제거되지 않는 한 어플리케이션 동작중 계속 유지

---

## 4. 의존관계 주입 (Dependency Injection, DI)

- **의존관계**: 객체 간의 연결
    - ex) A → B
- **의존관계 주입(DI**) :
    - 의존 객체를 외부에서 주입받는 방식
        - Singleton Scope : Application Context 초기화 시점에 발생
    - 다른 Scope : 런타임 시점에 Application Context가 객체 결정
    - 객체 간의 관계를 나타내고 의도를 표현
        - 특정 객체가 어떤 객체에 의존하는지 명시적으로 표현

### DI 방식

- **@Autowired :**
    - Spring에서 지원
        - Spring이 자동으로 Bean을 찾아서 주입
    - Spring Framework에 종속됨
    - @Autowired(required=false)로 필수 설정을 해제할 수 있음
- **@Inject :** java 표준으로 만들어진 Annotation이기에 특정 프레임워크에 종속되지 않음
1. **필드 주입**
    - 객체를 먼저 생성하고 생성된 객체를 나중에 주입하는 방식
        - == 객체 초기화 → 의존성 주입
    
    ```java
    @Autowired
    private EmailServiceClient emailServiceClient;
    ```
    
2. **생성자 주입**
    - 객체 생성시 의존성이 함께 주입
    
    ```java
    @Autowired
    public EmailController(EmailServiceClient emailServiceClient) {
        this.emailServiceClient = emailServiceClient;
    }
    ```
    
3. **세터 주입**
    - 객체 생성 후 스프링 컨테이너가 세터 메서드를 호출하며 주입
        - 세터 메서드 호출 시점에 의존성을 동시에 주입하려고 함
    
    ```java
    @Autowired
    public void setEmailServiceClient(EmailServiceClient emailServiceClient) {
        this.emailServiceClient = emailServiceClient;
    }
    ```
    
- **순환 의존성 (Circular Dependency)**
    - 두 Bean이 서로 의존할 때 순환 의존성 발생
    - **해결방법 :**
        - **@Lazy** 어노테이션 사용
        - 설계 단계에서 의존성 제거

---

## 5. AOP (Aspect-Oriented Programming)

- 관점 지향 프로그래밍
- 핵심 기능에서 부가 기능 분리하여 설계, 개발
    - 부가 기능(Aspect)을 별도 모듈로 관리
- 어플리케이션을 독립적으로 모델링, 설계, 개발가능

### Aspect

- 부가 기능 모듈
- 핵심 기능을 담고 있지는 않지만 공통 관심사항이 될 수는 있음
- **주요 용어**
    - **타겟(Target)**: 부가기능이 적용될 객체
    - **어드바이스(Advice)**: 타겟에 제공할 부가기능을 담은 모듈
    - **조인포인트(Join Point)**: 어드바이스 적용 가능 위치
        - joinPoint.getSignature().getName()
            - 포인트컷으로 선정된 메서드 이름
        - joinPoint.getSignature().getDeclaringTypeName()
            - 포인트컷으로 선정된 클래스 이름
        - joinPoint.getArgs()
            - 포인트컷으로 선정된 파라미터 값
    - **포인트컷(Pointcut)**: 특정 조건으로 객체의 메서드를 선정
- Annotation 기반 AOP 설정
    - 의존성 : spring-aop, aspectjrt, aspectjweaver
- 주요 Annotation

| 어노테이션 | 설명 | 예제 |
| --- | --- | --- |
| @Before | 메서드 실행 이전에 동작하는 어드바이스를 정의 | @Before("execution(* com.example.demo.UserService.*(..))") |
| @After | 메서드 실행 후에(성공 여부와 상관없이) 동작하는 어드바이스를 정의 | @After("execution(* com.example.demo.UserService.*(..))") |
| @AfterReturning | 메서드가 정상적으로 실행된 후 동작하는 어드바이스를 정의 | @AfterReturning("execution(* com.example.demo.UserService.*(..))") |
| @AfterThrowing | 메서드 실행 중 예외가 발생했을 때 동작하는 어드바이스를 정의 | @AfterThrowing("execution(* com.example.demo.UserService.*(..))") |
| @Around | 대상 메서드 실행 전후 모두 동작하며, 메서드 실행을 제어하거나 반환 값 수정 가능 | @Around("execution(* com.example.demo.UserService.*(..))") |
| @Pointcut | 공통으로 사용될 포인트컷 표현식을 재사용 가능하게 정의 | @Pointcut("execution(* com.example.demo.UserService.*(..))") |
- **활용**
    - **로깅**: 메서드 호출 전후 로깅
    - **트랜잭션 관리**: 데이터베이스 작업 전후에 트랜잭션 처리
    - **보안**: 메서드 호출 전 사용자 권한 확인
    - **모니터링**: 성능 측정, 메서드 실행 시간 기록
- 예제
    - UserService
        
        ```java
        import org.springframework.stereotype.Service;
        
        @Service
        public class UserService {
            public void getUserById(String userId) {
                System.out.println("Fetching user with ID: " + userId);
            }
        
            public void createUser(String userName) {
                System.out.println("Creating user: " + userName);
            }
        }
        
        ```
        
    - LoggingAspect
        
        ```java
        import org.aspectj.lang.annotation.Aspect;
        import org.aspectj.lang.annotation.Before;
        import org.aspectj.lang.annotation.After;
        import org.aspectj.lang.annotation.AfterReturning;
        import org.springframework.stereotype.Component;
        
        @Aspect
        @Component
        public class LoggingAspect {
        
            // 실행 전에 로깅
            @Before("execution(* com.example.demo.UserService.*(..))")
            public void logBefore() {
                System.out.println("[LOG] Method is about to execute...");
            }
        
            // 실행 후 로깅
            @After("execution(* com.example.demo.UserService.*(..))")
            public void logAfter() {
                System.out.println("[LOG] Method execution completed.");
            }
        
            // 정상적으로 반환되었을 때 로깅
            @AfterReturning("execution(* com.example.demo.UserService.*(..))")
            public void logAfterReturning() {
                System.out.println("[LOG] Method executed successfully.");
            }
        }
        
        ```
        
    - DemoApplication
        
        ```java
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.ApplicationContext;
        
        @SpringBootApplication
        public class DemoApplication {
            public static void main(String[] args) {
                ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        
                UserService userService = context.getBean(UserService.class);
                userService.getUserById("123");
                userService.createUser("Alice");
            }
        }
        -> 
        [LOG] Method is about to execute...
        Fetching user with ID: 123
        [LOG] Method execution completed.
        [LOG] Method executed successfully.
        
        [LOG] Method is about to execute...
        Creating user: Alice
        [LOG] Method execution completed.
        [LOG] Method executed successfully.
        
        ```