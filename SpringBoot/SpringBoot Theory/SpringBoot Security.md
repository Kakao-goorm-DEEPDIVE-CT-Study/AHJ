## **1. 스프링 시큐리티(Spring Security)란?**

- 스프링 기반 애플리케이션의 보안(인증, 인가, 권한)을 담당하는 하위 프레임워크
- **인증(Authentication)**
    - 사용자의 신원을 확인하는 과정
    - ex) 사용자의 ID와 비밀번호가 올바른지 확인
- **인가(Authorization)**
    - 권한 확인 과정
    - ex) 관리자 페이지는 관리자만 접근 가능.
- 다양한 보안 공격 방어: CSRF, 세션 고정, XSS 등
- Annotation으로 보안 규칙 정의 가능
    - 최소 설정으로 강력한 보안 적용 가능
    - @EnableWebSecurity
        - 스프링 시큐리티 활성화 Annotation
        - 보안 설정할 클래스에서 사용
        - 모든 요청이 인증을 요구하도록 설정됨
    - @PreAuthoirze
        - 메서드 단위로 인가 규칙 정의
        - 메서드 실행 전 특정 조건 확인
        - 예시
            
            ```java
            @PreAuthorize("hasRole('ADMIN')")
            public void adminOnlyMethod() {
            }
            ```
            
    - @PostAuthorize
        - 메서드 실행 후 특정 조건 체크
        - 반환 값을 기준으로 정책 적용
        - 예시
            
            ```java
            @PostAuthorize("returnObject.owner == authentication.name")
            public Document getDocument(Long id) {
            }
            ```
            
    - @Secured
        - 특정 역할만 접근 가능하도록 설정
        - ROLE_ 접두사 사용
        - 예시
            
            ```java
            @Secured("ROLE_ADMIN")// 관리자만 실행 가능
            public void adminAccess() {
            }
            ```
            
    - @RolesAllowed
        - 여러 역할을 동시에 지정 가능
        - 예시
            
            ```java
            @RolesAllowed({"ROLE_ADMIN", "ROLE_MANAGER"})// 관리자와 매니저 모두 접근 가능
            public void multiRoleAccess() {
            }
            ```
            

## 2. 스프링 시큐리티 주요 기능

- **CSRF 보호**
    - CSRF 공격 방지를 위한 CSRF 토큰 자동 활성화
    - POST, PUT, DELETE와 같은 상태 변경 요청에 적용
    - CSRF 토큰은 세션과 연결되어 외부 공격 차단
- **세션 고정 방지**
    - 세션 ID를 고정하지 않고, 새 세션 생성으로 보안 강화
- **HTTP 헤더 보안**
    - 다양한 HTTP 헤더 설정으로 보안 강화
    - 기본 활성화된 보안 헤더:
        - **X-Frame-Options**:
            - Clickjacking 공격 방지
                - 웹 페이지를 html의 iframe으로 삽입해 웹사용자가 악의적인 버튼을 클릭하도록 유도
            - 설정 값 :
                - DENY : 모든 도메인에서 iframe 삽입 금지
                - SAMEORIGIN : 동일 도메인에서만 iframe 삽입 허용
                - ALLOW-FROM uri : 특정 도메인에서만 iframe 삽입 허용
            - 예시
                
                ```java
                X-Frame-Options: SAMEORIGIN
                ```
                
        - **X-XSS-Protection**:
            - XSS 공격 방지
                - 스크립트 코드를 삽입해 개발자의 의도와 다른 기능이 작동하게 하는 공격
                - 브라우저의 XSS(Cross Site Scripting) 필터를 활성화 하여 XSS 공격 방어 기능 활성화
            - 방어 방법
                - 입력값 검증, 인코딩
                    - 사용자 입력값 검증, 허용된 값만 처리
                - Content Security Policy(CSP)
                    - 스크립트 실행을 제한하여 악성 코드 실행 방지
                - 출력 시 이스케이프 처리
                    - HTML, JS에서 특수문자를 이스케이프 처리
                        - 스프링, 대부분의 템플릿 엔진(ex Thymeleaf)는 자동 이스케이프 처리 지원
                    - 예:
                        - < : &lt;
                        - > : &gt;
                        - & : &amp;
                        - " : &quot;
                    - 예시
                        
                        ```html
                        적용전
                        <script>alert('악성 코드')</script>
                        
                        적용후
                        &lt;script&gt;alert('악성 코드')&lt;/script&gt;
                        ```
                        
            - 설정 값 :
                - 0 : XSS 필터 비활성화
                - 1 : XSS 필터 활성화
            - 예시
                
                ```java
                X-XSS-Protection: 1; mode=block
                ```
                
        - **HSTS (HTTP Strict Transport Security):**
            - HTTPS 사용 강제
            - HSTS 헤더로 브라우저가 HTTP 대신 HTTPS 사용하도록 지시
                - HTTP 요청 시 자동으로 HTTPS로 리다이렉트
            - 예시
                
                ```java
                Strict-Transport-Security: max-age=31536000; includeSubDomains
                ```
                
        - **Content-Security-Policy (CSP)**
            - 스크립트, 스타일, 이미지 등 리소스 로드 정책을 설정
            - 허용되지 않은 리소스 실행 방지
            - HTTP 응답 헤더에 Content-Security-Policy 추가
            - 예시
                
                ```java
                Content-Security-Policy: default-src 'self'; script-src 'self' https://trusted.com;
                ```
                
                - default-src 'self' : 기본 리소스는 자기 서버에서만 로드
                - script-src 'self' url : 스크립트는 자기 서버와 url에서만 실행

## 3. Spring Boot 애플리케이션의 로그인 및 로그아웃 프로세스

- **로그인**
    
    
    1. 웹 브라우저에서 /login 요청 전송
    2. UserController : 클라이언트의 요청을 수신
    3. UserDetailService : 사용자 정보를 불러오기 위해 loadUserByUsername()메서드 호출
        - UserRepository를 사용해 데이터베이스에서 사용자 정보 조회
        - 비밀번호는 BCryptPasswordEncoder를 통해 암호화
    4. 사용자 정보, 비밀번호를 이용해 WebSecurityConfig에 설정된 인증/인가 규칙에 따라 인증 과정 수행
    5. 인증 성공시 사용자 세션 생성
    6. 응답을 통해 클라이언트에 로그인 상태 전달
- **로그아웃**
    
    
    1. 웹 브라우저에 /logout 요청을 전송
    2. UserController : 로그아웃 요청을 수신
    3. SecurityContextLogoutHandler : logout()메서드를 호출하여 인증 세션 종료
        - 사용자 세션, 인증 정보 제거
    4. 로그아웃 성공 응답이 클라이언트로 전달

### 로그인 세부 로직
**1. HTTP 요청 (사용자 → 서버)**

- 사용자가 아이디와 비밀번호를 입력한 후 서버로 HTTP 요청을 보냄

**2. AuthenticationFilter**

- HTTP 요청을 가로채서 인증 절차를 시작
    - 유효한지 확인
- UsernamePasswordAuthenticationToken 객체 생성
    - 사용자가 입력한 아이디와 비밀번호가 포함된 객체

**3. AuthenticationManager**

- 인증 요청을 AuthenticationManager로 전달
- 이 인터페이스는 인증 작업의 시작점 역할을 하며 ProviderManager를 구현하여 사용

**4. ProviderManager**

- 요청받은 인증을 적절한 AuthenticationProvider로 위임
- 여러 AuthenticationProvider 중 요청에 맞는 것을 선택

**5. AuthenticationProvider**

- 인증의 핵심 작업 수행
- 사용자의 인증 정보를 UserDetailsService에 위임하여 아이디와 비밀번호 확인

**6. UserDetailsService**

- 데이터베이스 또는 기타 저장소에서 사용자 정보를 조회
- 사용자 정보를 담은 UserDetails 객체를 반환

**7. UserDetails**

- UserDetailsService가 반환한 UserDetails 객체에는 사용자의 아이디, 비밀번호, 권한 정보 등이 포함
- AuthenticationProvider는 이 정보를 바탕으로 사용자 비밀번호를 비교

**8. 인증 결과 반환**

- 인증 성공 시 Authentication 객체 생성
- 이 객체에는 사용자 정보와 권한 정보가 포함

**9. SecurityContext**

- SecurityContext는 현재 인증된 사용자 정보를 저장하는 컨텍스트
- 인증이 성공하면 Authentication 객체를 SecurityContext에 저장

**10. SecurityContextHolder**

- SecurityContextHolder는 SecurityContext를 전역적으로 관리
- 인증 정보는 이후 요청 시 재사용 가능

## 4. 스프링 시큐리티 필터


| **필터명** | **역할** | **설명** |
| --- | --- | --- |
| **SecurityContextPersistenceFilter** | 보안 컨텍스트 지속성 필터 | SecurityContextRepository에서 SecurityContext(접근 주체와 인증 정보를 담은 객체)를 가져오거나 저장. |
| **LogoutFilter** | 로그아웃 필터 | 설정된 로그아웃 URL로 오는 요청을 확인해 사용자를 로그아웃 처리. |
| **UsernamePasswordAuthenticationFilter** | 사용자 이름 비밀번호 인증 필터 | 폼 기반 로그인 시 아이디, 비밀번호 데이터를 파싱해 인증 요청 위임. 성공 시 AuthenticationSuccessHandler, 실패 시 AuthenticationFailureHandler 실행. |
| **DefaultLoginPageGeneratingFilter** | 기본 로그인 페이지 생성 필터 | 로그인 페이지를 별도로 지정하지 않았을 경우 기본 로그인 페이지를 생성. |
| **BasicAuthenticationFilter** | 기본 인증 필터 | 요청 헤더에서 아이디와 비밀번호를 파싱해 인증 요청 위임. 성공 시 AuthenticationSuccessHandler, 실패 시 AuthenticationFailureHandler 실행. |
| **RequestCacheAwareFilter** | 요청 캐시 처리 필터 | 로그인 성공 후 이전에 접근했던 페이지(캐시된 요청)를 기억하고 해당 페이지로 이동 처리. |
| **SecurityContextHolderAwareRequestFilter** | 보안 컨텍스트 요청 필터 | HttpServletRequest 정보를 감싸 다음 필터에 부가 정보를 제공. |
| **AnonymousAuthenticationFilter** | 익명 인증 필터 | 인증되지 않은 사용자를 위한 익명 객체(AnonymousAuthentication)를 생성해 SecurityContext에 추가. |
| **SessionManagementFilter** | 세션 관리 필터 | 세션 변조 방지, 유효하지 않은 세션 처리, 세션 생성 전략 설정 등 세션 관련 작업 수행. |
| **ExceptionTranslationFilter** | 예외 처리 필터 | 요청 처리 중 발생하는 예외를 번역하거나 전달. |
| **FilterSecurityInterceptor** | 보안 인터셉터 | AccessDecisionManager를 통해 접근 제어를 위임하며, 인가 관련 설정 처리. |


