<%--
  Created by IntelliJ IDEA.
  User: ahnhyeongjun
  Date: 24. 11. 25.
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
<form:form modelAttribute="user">
    이메일 : <form:input path="email"/> <br>
    비밀번호 : <form:password path="password"/><br>
    <input type="submit" value="로그인"/>
</form:form>

<h2> ${message}</h2>
</body>
</html>
