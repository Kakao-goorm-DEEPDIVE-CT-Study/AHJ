<%--
  Created by IntelliJ IDEA.
  User: ahnhyeongjun
  Date: 24. 11. 25.
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login Form</title>
</head>
<body>
<form action="/user/loginScope" method="post">
  이메일 : <input type="text" name="email"><br>
  비밀번호 :  <input type="password" name="password"><br>
  <button type="submit">로그인</button>
</form>
<h2> ${message}</h2>
</body>
</html>
