## Servlet


- Java에서 서버 웹 애플리케이션 개발을 위해 제공되는 기술
- 클라이언트의 HTTP요청, 응답을 생성하는 Java 클래스

**✅ 역할**

- 클라이언트 요청 처리
    - 브라우저, 클라이언트가 HTTP 요청을 보내면 서블릿이 처리
- 비즈니스 로직 수행
    - 요청에 따라 작업 처리
- 응답 생성
    - HTML, JSON, XML등의 형식으로 응답 반환

**✅ 작동방식**

- 클라이언트가 URL을 통해 요청
- 컨테이너가 해당 요청을 받은 후 적절한 서블릿에 전달
- 서블릿의 service()메서드가 호출되어 요청 처리
- 클라이언트에 응답 반환

**✅ 주요 메서드**

- init()
    - 서블릿 초기화 작업 수행
- service(HttpServletRequest req, HttpServletResponse res);
    - 클라이언트 요청 처리
    - HTTP 메서드에 따라 doGet(),doPost() 호출
- doGet(HttpServletRequest req, HttpServletResponse res);
    - HTTP GET 요청 처리
- doPost(HttpServletRequest req, HttpServletResponse res);
    - HTTP POST 요청 처리
- destroy()
    - 서블릿이 제거될 때 호출
- 예제
    
    ```java
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    
    import java.io.IOException;
    
    // URL 매핑
    @WebServlet("/hello")
    public class HelloServlet extends HttpServlet {
    
        @Override
        public void init() throws ServletException {
            // 초기화 작업 (서블릿이 로드될 때 한 번 호출)
            System.out.println("HelloServlet initialized!");
        }
    
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            // HTTP GET 요청 처리
            resp.setContentType("text/html");
            resp.getWriter().write("<h1>Hello, GET request!</h1>");
        }
    
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            // HTTP POST 요청 처리
            String name = req.getParameter("name"); // 폼 데이터에서 'name' 파라미터 가져오기
            resp.setContentType("text/html");
            resp.getWriter().write("<h1>Hello, " + name + "!</h1>");
        }
    
        @Override
        public void destroy() {
            // 서블릿이 종료될 때 호출 (자원 해제 작업)
            System.out.println("HelloServlet destroyed!");
        }
    }
    ```
    

**✅ 매핑**

- xml 설정
    
    ```xml
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.example.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```
    
- 어노테이션 설정
    
    ```java
    @WebServlet("/hello")
    public class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
            res.getWriter().write("Hello, Servlet!");
        }
    }
    ```
    

**✅ 단점**

- HTML, Java가 혼합되어 있음
- JSP, Spring에 비해 덜 직관적

## JSP(JavaServer Pages)


- 서버 측에서 동적으로 HTML, XML등을 생성하기 위해 사용하는 기술

**✅ 특징**

- HTML과 Java코드를 함께 사용할 수 있도록 설계됨
    - <% %>, <%= %> 태그를 사용해서 Java코드를 HTML에 포함
- 서블릿 기반 기술
    - JSP페이지는 컴파일시 서블릿 클래스로 변환
- 동적 웹 콘텐츠 생성
    - 데이터베이스와의 상호작용
    - 사용자 입력 처리
    - API 호출
- JSTL, 커스텀 태그 지원
    - 사용자 정의 태그를 활용하여 반복문, 조건문등의 로직을 HTML코드처럼 사용 가능

**✅ 동작 과정**

- model1 아키텍쳐
    - 클라이언트 요청
        - 사용자가 JSP 파일 요청
    - JSP 컴파일
        - JSP 파일 최초 요청시 서블릿 클래스로 컴파일
        - 이후 요청은 서블릿으로 실행
    - JavaBean 호출
        - JSP가 필요한 데이터를 전달(Controller)
        - 데이터 처리, 비즈니스 로직 수행(Service)
    - HTML 응답 반환
        - JavaBean에서 데이터를 가져와서 생성(View)
- model2 아키텍쳐
    - 클라이언트 요청
        - 사용자가 서블릿에 요청
    - 서블릿(Controller)
        - HTTP 메서드에 따라 요청 처리
        - 비즈니스 로직을 JavaBean에 위임
    - JavaBean 호출
        - 데이터 처리, 비즈니스 로직 수행(Service)
    - 서블릿이 view를 선택
        - 서블릿은 JavaBean의 결과를 JSP에 전달
    - HTML 응답 반환
        - 전달받은 데이터를 사용해 HTML 생성(View)

**✅ 주요태그**

- Scriptlet
    - <% %> 태그 안에 Java코드 작성
    
    ```java
    <%
        int count = 5;
        out.println("Count: " + count);
    %
    ```
    
- Expression
    - <%= %> 태그를 사용하여 값을 출력
    
    ```java
    <p>Today: <%= new java.util.Date() %></p>
    ```
    
- Declaration
    - <%! %> 태그를 사용하여 멤버 변수나 메서드를 선언
    
    ```java
    <%!
        private String getMessage() {
            return "Hello, JSP!";
        }
    %>
    <p><%= getMessage() %></p>
    ```
    
- Directive
    - JSP 페이지 설정을 지정
    
    ```java
    <%@ page language="java" contentType="text/html; charset=UTF-8" %>
    <%@ include file="header.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    ```