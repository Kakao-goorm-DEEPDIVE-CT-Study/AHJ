## 1.  스프링 웹 스코프

- 스프링은 Bean을 다양한 Scope로 설정 가능
- **웹 스코프 :**  웹 애플리케이션 관련 빈 관리에 사용
- **주요 스코프:**
    - **싱글톤(Singleton)** :
        - 기본 스코프, 애플리케이션 전체에서 한 개의 인스턴스 유지
        - 스프링 빈 : 소문자로 시작하는 클래스이름
    - **프로토타입(Prototype)** :
        - 요청 시마다 새로운 인스턴스 생성
        - 프레임워크가 타입만 관리하고 요청을 받을 때마다 해당 클래스의 새 인스턴스를 생성
    - **웹 스코프(Web Scope)** : HTTP 요청,세션 등과 연관된 Bean 스코프

## 2. 주요 웹 스코프 종류

### **Request Scope**

- HTTP 요청마다 새로운 Bean 인스턴스 생성
- 해당 요청이 완료되면 인스턴스 소멸
- @RequestScope 사용
- 예제
    
    ```java
    @RequestScope
    @Component
    public class RequestBean {
        // 요청에 관련된 데이터 관리
    }
    ```
    

### **Session Scope**

- 클라이언트가 HTTP 세션을 생성하면 Bean 인스턴스 생성
- HTTP 세션 동안 Bean 인스턴스 유지
    - Application Context가 해당 인스턴스를 클라이언트의 세션과 연결시켜줌
    - 동일 클라이언트의 모든 요청(HTTP세션이 유지되어야 함)
        - 해당 Session Scope Bean에 저장된 데이터 사용 가능
        - 같은 Bean 인스턴스를 재사용
- **사용 사례**:
    - 사용자 인증 정보 저장(로그인)
    - 온라인 쇼핑 장바구니
- @SessionScope 사용
- 예제
    
    ```java
    @SessionScope
    @Component
    public class SessionBean {
        private String userId;
        // 로그인한 사용자 정보 유지
    }
    ```
    
- **주의 사항** :
    - 서버 메모리를 사용하기에 필요한 데이터만 세션에 저장
    - 적절한 세션 유효 시간 설정
        - 웹 서버에서 설정 가능
    - 세션 동시 사용 방지를 위해 보안 강화
    - 필요한 경우 코드에서 수동으로 세션을 무효화

### **Application Scope**

- 애플리케이션 실행 동안 Bean 인스턴스 유지
    - 앱이 실행되는 동안 사용할 수 있음
- 싱글톤 방식
    - 애플리케이션 컨텍스트에서 고유하게 존재
- 모든 사용자와 요청에서 동일 인스턴스 접근
- **사용 사례**:
    - 애플리케이션 전역 설정 데이터 관리
- @ApplicationScope 사용
- 예제
    
    ```java
    @ApplicationScope
    @Component
    public class ApplicationBean {
        private String globalConfig;
        // 앱 전역 데이터 관리
    }
    ```