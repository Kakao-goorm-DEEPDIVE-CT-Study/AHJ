## AJAX

- 비동기적 데이터 통신을 가능하게 하는 기술

**✅ 특징**

- 비동기 통신
  - 서버와 데이터를 교환하는 동안 웹 페이지의 다른 작업이 중단되지 않음
  - 사용자 경험, 반응성 향상
- 웹 페이지를 새로 고침하지 않아도 서버와 데이터를 교환
  - 필요한 데이터만 교환하기에 네트워크 트래픽이 감소됨
  - DOM의 일부만 업데이트하는 것도 가능
- JSON 형식으로 데이터를 주고 받음

✅ 동작원리

- 클라이언트의 요청
  - JS가 XMLHttpRequest 객체 또는 Fetch API를 통해 서버에 요청
- 서버 응답
  - 데이터를 XML, JSON, HTML, TEXT등으로 반환
- 페이지 업데이트
  - JS는 응답데이터를 처리하고 필요한 DOM을 업데이트

**✅ 사용방법**

**1. XMLHttpRequest**

```jsx
const xhr = new XMLHttpRequest();
xhr.open("GET", "/api/data", true);
xhr.onreadystatechange = function () {
  if (xhr.readyState === 4 && xhr.status === 200) {
    console.log(xhr.responseText); // 서버 응답 처리
  }
};
xhr.send();
```

**2. Fetch API (현대적 방식)**

```jsx
fetch("/api/data")
  .then((response) => response.json())
  .then((data) => {
    console.log(data); // 서버 응답 처리
  })
  .catch((error) => {
    console.error("Error:", error);
  });
```

**3. jQuery AJAX (간결함)**

```jsx
$.ajax({
  url: "/api/data",
  method: "GET",
  success: function (data) {
    console.log(data); // 서버 응답 처리
  },
  error: function (error) {
    console.error("Error:", error);
  },
});
```
