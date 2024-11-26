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

### **스프링 부트에서 H2 데이터베이스 설정**

1. **의존성 추가 (build.gradle)**
    
    ```xml
    dependencies {
        implementation 'com.h2database:h2'
    }
    ```
    
2. **application.properties 설정**
    
    ```
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driver-class-name=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.h2.console.enabled=true
    spring.jpa.hibernate.ddl-auto=validate
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
    
2. **리포지토리 인터페이스**
    
    ```java
    package com.example.catwiki.repository;
    
    import org.apache.catalina.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    //<Entity, Key>
    public interface UserRepository extends JpaRepository<User, Long> {
    }
    
    ```
    
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