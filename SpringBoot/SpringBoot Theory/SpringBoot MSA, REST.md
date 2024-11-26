## **MSA (Microservice Architecture)**

- 대규모 애플리케이션을 작은 독립적인 서비스(마이크로서비스)로 분리해 개발
- 각 서비스는 독립적으로 배포, 개발, 유지보수가 가능

**특징**

- **독립성** : 각 서비스는 독립적으로 배포 및 운영
- **작고 명확한 역할** : 서비스마다 단일 책임 원칙 적용
- **분산 시스템** : 네트워크를 통해 상호작용
- **기술적 다양성** : 각 서비스에 적합한 기술 스택 사용 가능


## **REST (Representational State Transfer)**

- HTTP 프로토콜을 기반으로 리소스를 주고받는 웹 아키텍처 스타일

**특징**

- **HTTP 기반**: 요청과 응답 모두 HTTP를 사용
- **무상태성 (Stateless)**:
    - 서는 클라이언트의 상태를 저장하지 않음
    - 모든 정보는 요청에 포함
- **리소스 식별**: URI로 고유 식별
- **API 호출 규약 :**
    - 명확하고 표준화된 규칙을 따라야 함
    - 사용자가 쉽게 이해할 수 있도록 설계되어야 함
- **HTTP 메서드 사용**:
    - **GET**: 리소스 조회
    - **POST**: 리소스 생성
    - **PUT**: 리소스 수정
    - **DELETE**: 리소스 삭제
- **캐싱 가능**: 응답 데이터 캐싱으로 성능 향상

**REST API 설계 시 고려사항**

- **자원 명명(Naming Resources)**: 소문자, 복수형, 명사 사용
    - ex) /users, /orders
- **버전 관리**: URI에 버전 포함하여 클라이언트와 호환성을 유지
    - ex) /api/v1/users
- **상태 코드 활용**: 요청 결과를 HTTP 상태코드를 활용해 명확히 전달
    - 200 OK : 성공적으로 요청 처리
    - 201 Created : 새로운 리소스가 성공적으로 생성됨
    - 400 Bad Request : 잘못된 요청
    - 401 Unauthorized: 인증이 필요하지만 제공되지 않거나 잘못됨
    - 404 Not Found: 요청한 리소스가 존재하지 않음

**REST vs RESTful API**

- **REST**: 아키텍처 스타일
- **RESTful API**: REST 원칙을 준수한 API

**MSA에서 REST의 역할**

- **서비스 간 통신**: 독립된 마이크로서비스가 REST API를 통해 상호작용
- **인터페이스 표준화**: HTTP 기반으로 표준화된 인터페이스 제공
- **분산 시스템에서 유연성**: 서로 다른 언어, 플랫폼 간 상호운용 가능

### **Spring을 이용한 REST API 구현**

- 스프링 MVC의 Dispatcher Servlet을 사용하는것은 같음
- View Resolver가 없어 View를 찾지 않고 클라이언트에 JSON으로 응답
- REST 서비스 구현이 간단해짐

### **구현 단계**

1. **Controller에서 엔드포인트 정의**
    - @RestController로 클래스 선언
    - @RequestMapping 또는 HTTP 메서드 별 매핑 애너테이션 사용 (@GetMapping, @PostMapping 등)
2. **요청 데이터 처리**
    - @RequestBody: 클라이언트 JSON 데이터를 자바 객체로 변환
    - @PathVariable, @RequestParam: URI 경로, 쿼리 파라미터로 데이터 받음
3. **응답 데이터 처리**
    - @ResponseBody: 자바 객체를 JSON 형식으로 변환해 반환