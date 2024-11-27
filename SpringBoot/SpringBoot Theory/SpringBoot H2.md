## **1. H2 인메모리 데이터베이스**

**개념**

- Java 기반 경량 RDBMS
    - 애플리케이션 실행 시 메모리에 데이터를 저장
- 애플리케이션 종료 시 데이터가 사라짐
- 주로 테스트 및 개발 환경에서 사용

**장점**

1. **빠른 실행 속도**: 데이터가 메모리에 저장되어 빠른 읽기, 쓰기 가능
2. **별도의 설치 불필요**: .jar 파일로 간편하게 실행
3. **스프링 부트와 완벽 통합**: 자동 설정 및 간단한 구성 가능
4. **사용 사례**:
    - 유닛 테스트에서 임시 데이터베이스로 활용
    - 프로토타이핑 및 데이터 모델 설계
    - 개발 및 학습용

## **2. H2 데이터베이스 구성**

⚠️ @SpringBootApplication가 위치한 패키지와 그 하위 패키지만 스캔되므로, 파일이 잘못된 패키지에 위치하면 Spring Boot가 해당 클래스를 인식하지 못함

- @Entity, @Table이 있는 클래스를 인식하지 못하면 테이블이 생성되지 않음

### **스프링 부트에서 H2 데이터베이스 설정**

1. **의존성 추가 (build.gradle)**
    
    ```xml
    dependencies {
        implementation 'com.h2database:h2'
    }
    ```
    
2. **application.properties 설정**
    
    ```
    spring.application.name=cat-wiki
    # 애플리케이션 이름 설정. 로깅 또는 관리 콘솔에서 애플리케이션 이름으로 표시됨.
    
    # db
    spring.datasource.url=jdbc:h2:mem:testdb
    # H2 데이터베이스의 메모리 모드 URL 설정. 애플리케이션 종료 시 데이터가 삭제됨.
    
    spring.datasource.driver-class-name=org.h2.Driver
    # H2 데이터베이스를 사용할 드라이버 클래스 설정.
    # 드라이버 클래스 : 애플리케이션이 특정 DB에 연결하고 데이터를 읽고 쓰는 작업을 지원하는 컴포넌트
    
    spring.datasource.username=sa
    # 데이터베이스 사용자 이름 설정. H2의 기본 사용자 이름은 'sa'.
    
    spring.datasource.password=
    # 데이터베이스 비밀번호 설정. H2의 기본 비밀번호는 빈 문자열.
    
    spring.h2.console.enabled=true
    # H2 데이터베이스 웹 콘솔 활성화 설정. true로 설정하면 /h2-console에서 접속 가능.
    
    spring.jpa.hibernate.ddl-auto=update
    # JPA와 Hibernate의 테이블 생성 및 업데이트 정책 설정:
    # - none: 아무 작업도 수행하지 않음.
    # - validate: 엔티티와 데이터베이스 스키마를 비교해 유효성만 검사.
    # - update: 스키마를 현재 엔티티에 맞게 업데이트. 데이터는 유지.
    # - create: 애플리케이션 시작 시 스키마를 생성. 기존 데이터 삭제.
    # - create-drop: 시작 시 스키마 생성, 애플리케이션 종료 시 삭제.
    
    ```
    
3. **H2 콘솔 접속**
    - URL: http://localhost:8080/h2-console
    - JDBC URL: jdbc:h2:mem:testdb
    - Username: sa
    - Password: (빈 칸)
    - connect 클릭

---

### **REST 서비스와 H2 연동**

### **Spring Data JPA 설정**

1. **엔티티 클래스**
    
    ```java
    package com.example.catwiki.model;
    
    import jakarta.persistence.*;
    
    @Entity
    @Table(name ="users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
    
        //Getter / Setter
        }
    }
    
    ```
    
    - **@Entity**
        - 해당 클래스가 JPA에서 관리하는 엔티티 클래스임을 나타냄
            - 데이터베이스와 상호작용할 수 있는 기반을 제공
        - 적어도 하나의 @Id를 포함해야 함
    - **@Table**
        - 엔티티를 데이터베이스의 테이블에 매핑
        - 테이블 이름, 스키마, 고유 제약 조건등을 지정할때 사용
        - 생략가능
            - 생략시 클래스 이름과 동일하게 생성
    - **@Id**
        - 엔티티의 기본 키
        - 해당 필드를 기준으로 엔티티 식별
    - **@GeneratedValue**
        - 기본 값을 자동으로 생성하도록 지정
        - GenerationType.IDENTITY:
            - 데이터베이스의 AUTO_INCREMENT 기능을 사용하여 기본 키 값을 자동으로 생성
            - 데이터베이스가 각 행의 ID를 관리
        - GenerationType.AUTO:
            - JPA 구현체가 생성 전략을 자동으로 선택
        - GenerationType.SEQUENCE:
            - 데이터베이스 시퀀스를 사용하여 기본 키 값을 생성
            - 주로 Oracle, PostgreSQL에서 사용
        - GenerationType.TABLE:
            - 테이블을 사용하여 기본 키 값을 관리
2. **리포지토리 인터페이스**
    
    ```java
    package com.example.catwiki.repository;
    
    import org.apache.catalina.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    //<Entity, Key>
    public interface UserRepository extends JpaRepository<User, Long> {
    }
    
    ```
    
    - JpaRepository는 JPA에서 제공하는 기본 기능(데이터 저장, 조회 등)을 사용할 수 있게 지원
    - <User, Long>
        - User: 데이터베이스 테이블과 연결된 엔티티 클래스.
        - Long: 기본 키(id)의 타입
    - UserRepository에 저장/조회 요청을 하면 Spring Data JPA가 자동으로 SQL을 생성하고 데이터베이스와 통신
3. **REST 컨트롤러**
    
    ```java
    package com.example.catwiki.controller;
    
    import com.example.catwiki.repository.UserRepository;
    import org.apache.catalina.User;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @RequestMapping("/users")
    public class UserController {
    
        private final UserRepository userRepository;
    
        public UserController(UserRepository userRepository){
            this.userRepository = userRepository;
        }
    
        @GetMapping
        public List<User> getAllUsers(){
            return userRepository.findAll();
        }
    
        @PostMapping
        public User createUser(@RequestBody User user){
            return userRepository.save(user);
        }
    }
    
    ```
    

## 3. JPA흐름

**JPA (Java Persistence API)**:

- 자바 객체(클래스)를 데이터베이스 테이블과 연결해주는 역할
- SQL을 직접 작성하지 않고도 데이터를 저장하고 불러올 수 있음

**Spring Data JPA**:

- JPA를 더 쉽게 사용할 수 있도록 도와주는 도구
- 반복적으로 작성해야 할 코드를 대신 작성

1. **사용자가 데이터를 POST 요청**

    ```json
    POST /users
    {
        "name": "Alice",
        "email": "alice@example.com"
    }
    ```

2. **컨트롤러에서 Repository를 호출**

    ```java
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user); // 저장 요청
    }

    ```

3. **Spring Data JPA의 동작**

- userRepository.save(user) 호출 시 Spring Data JPA가 자동으로 SQL 쿼리를 생성
    
    ```sql
    INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com');
    ```
    

4. **데이터베이스에 데이터 저장**

- 데이터베이스에 users 테이블이 있다면 다음과 같이 데이터가 저장
    
    ```sql
    | id  | name   | email             |
    |-----|--------|-------------------|
    | 1   | Alice  | alice@example.com |
    ```
    

5. **GET 요청으로 데이터 조회**

    ```java
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(); // 조회 요청
    }
    ```

- userRepository.findAll() 호출 시 Spring Data JPA가 자동으로 SQL 쿼리를 생성
    
    ```sql
    SELECT * FROM users;
    ```
    

6. **결과 반환**

- 데이터베이스에서 데이터를 조회한 뒤, Java 객체 리스트로 반환합니다:
    
    ```json
    [
        {
            "id": 1,
            "name": "Alice",
            "email": "alice@example.com"
        }
    ]
    ```