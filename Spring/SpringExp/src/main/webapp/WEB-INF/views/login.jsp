<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>로그인</title>
</head>
<body>
<h1>로그인</h1>
<form action="/login" method="post">
  ID: <input type="text" name="id"><br>
  비밀번호: <input type="password" name="password"><br>
  <button type="submit">로그인</button>
</form>

<!-- 회원가입 버튼 -->
<a href="/register">
  <button type="button">회원가입</button>
</a>

<!-- ID/비밀번호 찾기 버튼 -->
<a href="/find">
  <button type="button">ID/비밀번호 찾기</button>
</a>

<p>${message != null ? message : ''}</p>
</body>
</html>
