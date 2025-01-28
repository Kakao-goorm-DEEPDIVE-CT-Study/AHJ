## Thymeleaf

- Java 웹 애플리케이션에서 동적 HTML 콘텐츠를 생성하기 위해 사용되는 서버사이드 템플릿 엔진
    - 서버사이드 템플릿 엔진 : HTML, CSS, JS 코드에 동적으로 데이터를 삽입하는 도구
- HTML파일에 Java 객체의 데이터를 통합하여 사용자에게 동적으로 생성된 웹페이지 제공

**✅ 문법**

- **th:text**
    - 텍스트 변경
        - 기본 텍스트 → ${message} 대체
    
    ```html
    <span th:text="${message}">기본 텍스트</span>
    ```
    
- **th:href**
    - 링크 변경
        - ${id}에 값을 받아 URL 생성 → /example/123
    
    ```html
    <a th:href="@{/example/{id}(id=${id})}">링크</a>
    ```
    
- **th:src**
    - 이미지 경로 설정
    
    ```html
    <img th:src="@{/images/logo.png}" alt="로고 이미지">
    ```
    
- **th:if**
    - 조건부 렌더링(true)
    
    ```html
    <p th:if="${user.loggedIn}">환영합니다, ${user.name}!</p>
    ```
    
- **th:unless**
    - 조건부 렌더링(false)
    
    ```html
    <p th:unless="${user.loggedIn}">로그인 해주세요.</p>
    
    ```
    
- **th:each**
    - 반복 처리
    
    ```html
    <ul>
        <li th:each="item : ${items}" th:text="${item}">기본 아이템</li>
    </ul>
    ```
    

**✅ 표현식**

- **${}**
    - 변수 표현식
    - 기본 메시지 → ${message} 대체
    
    ```html
    <p th:text="${message}">기본 메시지</p>
    ```
    
- **#{}**
    - 메시지 표현식
    
    ```html
    <p th:text="#{welcome.message}">환영합니다.</p>
    ```
    
- ***{}**
    - 선택 표현식
    
    ```html
    <input type="text" th:value="*{name}">
    ```
    
- **@{}**
    - URL 표현식
    
    ```html
    <a th:href="@{/board/view/{id}(id=${board.id})}">게시글 보기</a>
    ```
    
- **~{}**
    - Fragment표현식
    
    ```html
    <div th:fragment="footer">
        <footer>Copyright 2025</footer>
    </div>
    
    <div th:insert="~{fragment :: footer}"></div>
    
    ```
## Thymeleaf 적용 방법

- build.gradle
    
    ```java
    dependencies{
    	developmentOnly 'org.springframework.boot:spring-boot-devtools'
    }
    ```
    
- application.properties
    
    ```html
    spring.thymeleaf.cache=false
    spring.devtools.livereload.enabled=true
    ```
    
    - spring.thymeleaf.cache=false
        - Spring boot는 Thymeleaf 템플릿 파일을 캐싱하여 성능을 최적화
        - 개발환경에서는 템플릿 수정과 즉각적인 반영이 필수이기에 false로 지정
    - spring.devtools.livereload.enabled=true
        - Spring boot Devtools에서 제공하는 LiveReload 기능을 활성화
            - 브라우저와 연결된 상태에서 HTML, CSS, JacaScript등이 변경되면 브라우저를 새로 고침함