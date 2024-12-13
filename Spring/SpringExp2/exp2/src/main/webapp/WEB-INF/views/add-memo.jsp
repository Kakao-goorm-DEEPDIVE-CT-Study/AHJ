<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Memo</title>
</head>
<body>
<h1>Add New Memo</h1>
<form action="/add" method="post">
  <label for="title">Title:</label><br />
  <input type="text" id="title" name="title" required /><br /><br />
  <label for="content">Content:</label><br />
  <textarea id="content" name="content" rows="5" cols="30" required></textarea><br /><br />
  <label for="writer">Writer:</label><br />
  <input type="text" id="writer" name="writer" required /><br /><br />
  <label for="writeDate">Write Date:</label><br />
  <input type="datetime-local" id="writeDate" name="writeDate" required /><br /><br />
  <button type="submit">Add Memo</button>
</form>
<form action="/memo" method="get">
  <button type="submit">Back to List</button>
</form>
</body>
</html>
