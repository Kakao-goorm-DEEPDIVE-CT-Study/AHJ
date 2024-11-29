## **1. 스프링 테스트**

스프링에서 테스트는 크게 단위 테스트(Unit Test)와 통합 테스트(Integration Test)로 구분

- **단위 테스트**:
    - 코드의 개별 단위(주로 메서드나 클래스)를 테스트.
    - 테스트 대상 코드의 외부 의존성을 최소화
        - Mockito, Mock 을 활용해 의존성 대체
    - **목적:** 특정 로직이 올바르게 동작하는지 확인.
    - **도구:** JUnit, Mockito.
- **통합 테스트**:
    - 애플리케이션의 여러 계층(Controller, Service, Repository 등)을 통합적으로 테스트
    - 실제 스프링 컨텍스트를 로드
    - 데이터 베이스와 같은 외부 리소스와 상호작용한 테스트 가능
    - **목적:** 실제 애플리케이션처럼 동작하는지 확인.
    - **도구:** @SpringBootTest.

## **2. JUnit**

- 자바의 대표적 테스트 프레임워크
    - 단위테스트, 통합테스트 모두 가능
- 스프링 테스트의 핵심 도구로, 스프링 애플리케이션의 다양한 부분을 독립적으로 테스트 가능.

### **JUnit 주요 어노테이션**

- **@Test**
    - 테스트 메서드를 정의할 때 사용.
    - 테스트 로직이 예상대로 동작하는지 검증.
    - 예제:
        
        ```java
        @Test
        void calculateSum() {
            int result = calculator.sum(1, 2);
            assertEquals(3, result); // 예상값과 실제값 비교
        }
        ```
        
1. **@DataJpaTest**
    - JPA 계층 테스트 최적화
        - JPA와 관련된 컴포넌트만 로드하여 JPA 테스트 속도를 높일 수 있음
    - 내장 DB(H2) 사용으로 테스트 독립성 유지
        - 실제 환경과 다를 수 있음
    - 테스트 후 자동으로 트랜잭션 롤백
        - 실제 데이터베이스에 영향을 주지 않음
    - 서비스 계층이나 컨트롤러 계층 테스트에는 부적합
        - JPA테스트에만 유효
    - 예제
        
        ```java
        @DataJpaTest
        class UserRepositoryTest {
            @Autowired
            private UserRepository userRepository;
        
            @Test
            void saveUserTest() {
                User user = new User("John", "Doe");
                User savedUser = userRepository.save(user);
                assertNotNull(savedUser.getId());
            }
        }
        ```
        
2. **@SpringBootTest**
    - 애플리케이션 전체를 통합 테스트할 때 사용.
    - 스프링 컨텍스트를 로드하여 애플리케이션 환경을 재현
        - 실제 환경에 가까운 테스트
    - @DataJpaTest보다 느리지만, 서비스 및 컨트롤러 계층을 포함한 전체 테스트에 적합.
    - 예제:
        
        ```java
        @SpringBootTest
        class ApplicationTest {
            @Autowired
            private UserService userService;
        
            @Test
            void createUserTest() {
                User user = userService.createUser("Jane", "Doe");
                assertEquals("Jane", user.getFirstName());
            }
        }
        ```
        
3. **@BeforeEach / @AfterEach**
    - 각 테스트 메서드 실행 전후에 실행될 코드를 정의.
    
    ```java
    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }
    
    @AfterEach
    void cleanup() {
        // 테스트 후 리소스 정리
    }
    ```
    
4. **@ParameterizedTest**
    - 다양한 입력값으로 반복 테스트 가능.
    
    ```java
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testIsEven(int number) {
        assertTrue(number % 2 == 0);
    }
    ```
    

## **3. 테스트 데이터 관리**

- 테스트는 실제 데이터베이스에 영향을 주지 않도록 설계해야 하며, 이를 위한 도구와 어노테이션이 제공
- **@Sql**
    - 테스트 전에 SQL 스크립트 실행으로 데이터 준비
    - 테이블 초기화나 테스트 데이터 삽입 가능
- 예제:
    
    ```java
    @Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    void testWithPreparedData() {
        // SQL로 준비된 데이터로 테스트 진행
    }
    ```
    



### **4. 테스트 주도 개발 (TDD)**

- TDD(Test-Driven Development)는 테스트를 먼저 작성
- 테스트를 통과시키는 방식으로 코드를 작성하는 개발 방법론입니다.
- **장점 :**
    - 결함, 문제점 초기 발견
    - 오구사항을 테스트로 명확히 정의
    - 코드 품질, 유지보수성 향상

### **TDD의 세 단계**

1. **Red (실패하는 테스트 작성)**
    - 코드 작성 전에 테스트 먼저 작성
    - 아직 구현된 코드가 없기에 테스트 실패
2. **Green (테스트 통과하기 위한 최소한의 코드 작성)**
    - 테스트 통과를 목표로 필요한 최소한의 기능 구현
3. **Refactor (코드 리팩토링)**
    - 테스트 통과 후 코드 정리 및 개선
    - 중복 제거, 가독성 향상 등 품질 개선
- 예제:
    
    ```java
    // 1. 실패하는 테스트 작성 (Red)
    @Test
    void addNumbers_shouldReturnSum() {
        int result = calculator.add(1, 2);
        assertEquals(3, result); // 실패
    }
    
    // 2. 테스트 통과를 위한 최소한의 코드 작성 (Green)
    public int add(int a, int b) {
        return a + b;
    }
    
    // 3. 코드 리팩토링 (Refactor)
    // 추가 기능 및 유지보수 작업 진행
    ```
    

## **5. 테스트 환경 설정**

- **의존성 추가 (Gradle)**
    - 테스트 관련 라이브러리를 build.gradle에 추가
    
    ```
    dependencies {
        testImplementation 'org.springframework.boot:spring-boot-starter-test'
        testImplementation 'org.mockito:mockito-core'
    }
    ```
    
- **application.properties**
    - 테스트 환경에서 사용할 설정:
    
    ```
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=create-drop
    ```