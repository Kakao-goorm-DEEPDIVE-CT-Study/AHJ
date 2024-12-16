## 1. MVC 패턴이란?

- **Model-View-Controller (MVC)**:
    - 뷰(View)와 데이터(Model)를 분리하고 흐름을 제어
        - 비즈니스 로직 분리
    - **Model**: 상태 정보 및 데이터 관련 로직 처리 계층
        - ≠ Spring의 Model객체
        - ex) User class
        - Service는 여러 모델을 조합해서 비즈니스 로직을 관리, 처리
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
    - default값, 상세한 제어를 하기 위함
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
    - 필수로 입력되는값이 있을때 사용
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
    - Controller에서 객체를 생성 후 Model에 담음
    - Model에 데이터 삽입 → View에 전송
        - user라는 이름으로 객체가 View에 전달
    - Spring Form을 이용해 user객체의 필드를 바인딩
        - 입력값 자동 매핑
    - Controller에서 @ModelAttribute를 사용하여 바인딩된 객체를 받을 수 있음
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
    

# **7. Spring MVC의 데이터 바인딩 원리**

- Spring의 @ModelAttribute와 데이터 바인딩 메커니즘을 통해 처리
- HTML <form> 태그의 name 속성을 통해 사용자가 입력한 데이터를 컨트롤러로 전송
- Spring MVC는 요청 데이터를 Java 객체(User)로 자동 매핑

**동작 과정**

1. JSP에서 데이터 전송
    - JSP에서 <form> 태그로 입력 데이터를 서버로 전송
    - 각 <input> 태그의 name 속성이 요청 파라미터의 키로 작동
    - <form>의 action 속성으로 요청 경로 지정
    
    ```html
    <form action="/register" method="post">
        ID: <input type="text" name="id"><br>
        비밀번호: <input type="password" name="password"><br>
        이메일: <input type="text" name="email"><br>
        <button type="submit">회원가입</button>
    </form>
    ```
    
2. 컨트롤러에서 데이터 처리
    - 컨트롤러 메서드에서 @ModelAttribute를 사용해 요청 데이터를 Java 객체로 매핑
    
    ```java
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model)
    ```
    
3. User 객체와 요청 데이터 매핑
    - Spring은 요청 파라미터(id, password, email)를 User 객체의 필드에 자동으로 매핑.
    - 매핑 기준
        - 요청 파라미터의 이름 == Java 객체의 필드 이름
        - 기본 생성자와 getter/setter가 필요.
    
    ```java
    @Data
    public class User {
        private String id;
        private String password;
        private String email;
    }
    ```
    
4. 결과 데이터 처리
    - 컨트롤러에서 model.addAttribute를 통해 JSP로 메시지를 전달
    - JSP에서 ${message}로 메시지를 출력
    
    ```java
    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        String result = userService.registerUser(user.getId(), user.getPassword(), user.getEmail());
        model.addAttribute("message", result);
        return result.equals("회원가입 성공") ? "redirect:/login" : "register";
    }
    ```
    
    ```html
    <p>${message != null ? message : ''}</p>
    ```
    

# 8. 리다이렉트란

- 서버가 클라이언트에게 새로운 URL로 요청을 다시 보내도록 지시하는 것
- 클라이언트는 서버의 응답을 받은 후 지정된 URL로 GET 요청
    - POST-Redirect-GET 패턴
- 이 과정에서 브라우저의 URL이 변경
- GET요청을 통해 항상 최신 데이터를 서버에 요청
- 리다이렉트는 두 번의 요청과 응답이 발생하며 네트워크 자원이 더 소모
- 중요한 변경 작업 이후 데이터 중복 처리를 방지하고, 항상 최신 상태를 유지하는 데 적합

```java
@PostMapping("/add")
public String addMemo(@ModelAttribute Memo memo) {
    memoService.addMemo(memo); // 데이터 추가
    return "redirect:/memo"; // 최신 메모 리스트로 이동
}
```

**일반 호출이란**

- 서버가 요청을 처리한 뒤 바로 응답을 반환하여 뷰를 렌더링
- 클라이언트는 새로운 요청을 보내지 않고 응답을 받음
- 이 과정에서 브라우저의 URL은 변경되지 않음
- 일반 호출은 한 번의 요청과 응답으로 끝나기에 리소스 소모가 적음
- 브라우저 새로고침 시 데이터가 중복 처리될 수 있는 단점 존재

```java
@PostMapping("/add")
public String addMemo(@ModelAttribute Memo memo, Model model) {
    memoService.addMemo(memo); // 데이터 추가
    model.addAttribute("memo-list", memoService.showMemoList()); // 최신 데이터 전달
    return "memo-list"; // 바로 메모 리스트 뷰 반환
}
```

**주요 차이점**

- 요청 흐름
    - 리다이렉트 : 클라이언트가 서버 응답을 받은 후 새 URL로 GET 요청을 다시 전송
    - 일반 호출 : 요청과 응답이 한 번에 종료
- URL 변경 여부
    - 리다이렉트 : 브라우저의 URL이 변경
    - 일반 호출 : 브라우저의 URL이 변경 X
- 데이터 전달
    - 리다이렉트 : 새로운 요청에서는 이전 요청 데이터를 사용할 수 없음
    - 일반 호출 : 같은 요청 내에서 데이터를 유지할 수 있음

**사용하는 상황과 고려 사항**

- 리다이렉트는 데이터가 변경되는 작업 후 최신 상태를 보여주고 싶을 때 사용
- ex) 메모를 삭제한 후 최신 리스트를 보여줄 때 적합 POST-Redirect-GET 패턴을 구현하여 데이터 중복 처리를 방지 가능
    
    ```java
    @PostMapping("/remove")
    public String removeMemo(@RequestParam int index) {
        memoService.removeMemo(index); // 데이터 삭제
        return "redirect:/memo"; // 최신 상태를 요청
    }
    ```
    
- 일반 호출은 데이터 변경 작업이 필요하지 않고 요청 데이터를 유지하며 바로 결과를 렌더링해야 할 때 사용
- ex) 특정 메모의 상세 정보를 보여줄 때 적합합니다
    
    ```java
    @GetMapping("/show")
    public String showMemo(@RequestParam int choice, Model model) {
        model.addAttribute("memo", memoService.getMemo(choice)); // 데이터 전달
        return "show-memo"; // 메모 상세 뷰 렌더링
    }
    ```
    

# 주요 어노테이션 정리

| 어노테이션 | 설명 |
| --- | --- |
| **@Controller** | 컨트롤러 클래스 정의 |
| **@RestController** | RESTful 웹 서비스에서 사용 @Controller, @ResponseBody 결합 |
| **@GetMapping** | HTTP GET 요청 매핑 |
| **@PostMapping** | HTTP POST 요청 매핑 |
| **@RequestBody** | 요청 본문을 자바 객체로 변환 |
| **@ResponseBody** | 응답 본문을 직접 반환 |
| **@ModelAttribute** | 모델 데이터 바인딩 및 전달 |
| **@SessionAttributes** | 모델 속성을 세션에 저장 |