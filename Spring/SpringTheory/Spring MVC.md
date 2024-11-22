## 1. MVC 패턴이란?

- **Model-View-Controller (MVC)**:
    - 뷰(View)와 데이터(Model)를 분리하고 흐름을 제어
        - 비즈니스 로직 분리
    - **Model**: 상태 정보 및 로직 처리
    - **View**: 결과 화면을 사용자가 볼 수 있게 표시
    - **Controller**: 사용자의 입력(URL)을 받아 흐름 제어

## 2. MVC 작동 원리

1. 클라이언트 요청을  Controller가 받음
2. Controller가 모델을 사용해 요청을 처리
3. Controller가 사용자에게 보여줄 View 선택
4. 실행 결과를 View에 전달
5. View에서 사용자에게 결과 화면 제공

## 3. MVC 특징

- Model(비즈니스 로직 처리)과 View(결과 화면 출력)를 분리
- Controller : 흐름 제어, 사용자 요청 처리
- 유지 보수 용이, 확장 가능

## 4. Spring MVC 구성

**DispatcherServlet**

- 모든 클라이언트 요청을 가로채는 중심 역할.
- 처리 흐름:
    1. DispatcherServlet : 클라이언트 요청을 가로챔 → HandlerMapping에게 정보 전송
    2. HandlerMapping : 적절한 Controller 찾기 → Controller에게 요청 전송
    3. Controller : 요청 처리후 View 이름 반환
    4. ViewResolver :  View 찾고 prefix, suffix 처리 → DispatcherServlet에 전송
        1. ex) prefix : /WEB-INF/views   suffix : .jsp
    5. 최종 결과(.jsp파일)를 DispatcherServlet 통해 클라이언트에게 응답.

**ViewResolver 설정 예제**

```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
   <property name="prefix" value="/WEB-INF/views/" />
   <property name="suffix" value=".jsp" />
</bean>

```

## 5. Controller 주요 어노테이션

**@RequestMapping**

- 클라이언트가 요청한 URL, HTTP메서드, 헤더등을 정의
- Controller 메서드와 매핑
- **String[] value**:
    - URL 패턴 정의
    - , 를 사용해 여러 URL 동시 사용 가능
    - ex) "/hello", “/a”
- **RequestMethod[] method**:
    - HTTP 메서드 지정
        - ex)GET, POST
    - 여러 메서드 동시 사용 가능
    - defalut method : GET
- **String[] params**: 요청받은 파라미터
- **String[] headers**: 요청받은 HTTP 헤더
- 예제
    
    ```java
    @RequestMapping("/hello")
    public String home(){
        return "hello";
    }
    
    @RequestMapping(value = "/requestMappingGetTest", method = RequestMethod.GET)
    public String requestMappingGetTest(Model model){
        model.addAttribute("textFromController","안녕하세요");
        return "requestHello";
    }
    ```
    

**@RequestParam**

- 클라이언트가 요청시 같이 보낸 데이터 정의
    - **String value/name**: 파라미터 이름
    - **required**: 파라미터의 필수 여부
        - default : true
    - **defaultValue**: 파라미터의 기본값 지정
- 예제
    
    ```java
     @RequestMapping("/requestParamTest")
    public String requestParamTest(
    		@RequestParam(name="a", required = false, defaultValue = "0") int a,
    		@RequestParam("b") String b,
    		@RequestParam(name = "c", defaultValue = "true") boolean c
    ){
    		System.out.println("a = " + a);
    		System.out.println("b = " + b);
    		System.out.println("c = " + c);
    		return "hello";
    }
    
    @GetMapping("/requestParamMapTest")
    public String requestParamMapTest(
    		@RequestParam Map<String,String> map
    ){
    		for(Map.Entry<String,String> entry : map.entrySet()){
    		System.out.println(entry.getKey() + " = " + entry.getValue());
    }
    		return "hello";
    }
    ```
    

**@PathVariable**

- REST형식의 URL에서 데이터를 추출할때 사용
    - **REST(Representational State Transfer)**
        - REST 아키텍쳐 원칙을 따르는 웹 서비스에서 사용하는 URL 설계 방식
        - URL에 데이터를 나타냄
            - **GET :** 리소스 조회
                - ex) GET /users/123 : ID가 123인 사용자 조회
            - **POST :** 리소스 생성
                - ex) POST /users/123 : ID가 123인 사용자 생성
            - **PUT :** 리소스 수정
                - ex) PUT /users/123 : ID가 123인 사용자 수정
            - **DELETE :** 리소스 삭제
                - ex) DELETE /users/123 : ID가 123인 사용자 삭제
        - 장점
            - 가독성
                - URL만으로 무슨 동작을 하는지 이해 가능
            - 표준화
                - 일관성 있는 URL 설계 가능
            - 유지보수 용이
                - 구조적이고 일관성 있는 설계 덕에 코드 관리가 쉬움
- 객체 형식으로 값을 전달받는 것
    - null일 경우 주의가 필요
- 예제
    
    ```java
    @GetMapping("/pathVariableTest/{a1}/{b}/{c}")
    public String pathVariableTest(
            @PathVariable(value = "a1") String a,
            @PathVariable String b,
            @PathVariable int c
    ){
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        return "hello";
    }
    ```
    

## 6. Spring Form

**스프링 폼 처리**

- Spring MVC 패턴을 충족하기 위한 Form태그 라이브러리
    - 입력값을 Controller에 전송
    - Model에 데이터 삽입 → View에 전송
- spring-form.tld가 필요하므로 태그 선언 필수
    
    ```
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    ```
    
- 예제
    
    ```java
    @GetMapping("/signup")
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }
    ```
    

**Response 처리**

- @ResponseBody
    - 리턴 데이터로 View 파일을 찾는 대신 클라이언트에 응답
    - ex) return "hello";
        - 순수 문자열 응답.

**Redirect**

- 다른 URL로 이동
    
    ```java
    return "redirect:/home";
    ```
    

## 주요 어노테이션 정리


| 어노테이션        | 설명                                                |
|-------------------|----------------------------------------------------|
| **@Controller**   | 컨트롤러 클래스 정의                                |
| **@RestController** | RESTful 웹 서비스에서 사용 @Controller, @ResponseBody 결합 |
| **@GetMapping**   | HTTP GET 요청 매핑                                 |
| **@PostMapping**  | HTTP POST 요청 매핑                                |
| **@RequestBody**  | 요청 본문을 자바 객체로 변환                       |
| **@ResponseBody** | 응답 본문을 직접 반환                              |
| **@ModelAttribute** | 모델 데이터 바인딩 및 전달                        |
| **@SessionAttributes** | 모델 속성을 세션에 저장                        |