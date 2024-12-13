<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Memo</title>
</head>
<body>
<h1>Edit Memo</h1>
<form action="/edit" method="post">
  <!-- index 값을 hidden 필드로 전달 -->
  <input type="hidden" name="index" value="${index}" />

  <label for="title">Title:</label><br />
  <input type="text" id="title" name="title" value="${memo.title}" required /><br /><br />

  <label for="content">Content:</label><br />
  <textarea id="content" name="content" rows="5" cols="30" required>${memo.content}</textarea><br /><br />

  <label for="writer">Writer:</label><br />
  <input type="text" id="writer" name="writer" value="${memo.writer}" required /><br /><br />

  <label for="writeDate">Write Date:</label><br />
  <input type="datetime-local" id="writeDate" name="writeDate" value="${memo.writeDate}" required /><br /><br />

  <button type="submit">Save Changes</button>
</form>

<form action="/memo" method="get">
  <button type="submit">Back to List</button>
</form>
</body>
</html>
