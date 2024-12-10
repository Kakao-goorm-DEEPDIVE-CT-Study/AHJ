<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ID/비밀번호 찾기</title>
</head>
<body>
<h1>ID/비밀번호 찾기</h1>
<form action="/find" method="post">
  <label for="email">이메일 입력:</label>
  <input type="email" name="email" id="email" required><br>
  <button type="submit">찾기</button>
</form>

<!-- 결과 메시지 출력 -->
<p>${message}</p>
<p>
  <strong>ID:</strong> ${user != null ? user.id : ''} <br>
  <strong>비밀번호:</strong> ${user != null ? user.password : ''}
</p>


<!-- 로그인 페이지로 돌아가기 버튼 -->
<a href="/login">로그인 페이지로 돌아가기</a>
</body>
</html>
