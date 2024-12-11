<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
</head>
<body>
<h1>스터디 카페 등록</h1>
<form action="/studycafe" method="post">
  <label for="name">이름:</label>
  <input type="text" id="name" name="name" required><br><br>

  <label for="code">코드:</label>
  <input type="text" id="code" name="code" required><br><br>

  <button type="submit">등록</button>
</form>
</body>
</html>
