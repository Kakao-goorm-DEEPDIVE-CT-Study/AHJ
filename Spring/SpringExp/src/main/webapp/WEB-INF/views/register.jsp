<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
</head>
<body>
<h1>회원가입</h1>
<form action="/register" method="post">
  ID: <input type="text" name="id"><br>
  비밀번호: <input type="password" name="password"><br>
  이메일: <input type="text" name="email"><br>
  <button type="submit">회원가입</button>
</form>
<p>${message != null ? message : ''}</p>
</body>
</html>
