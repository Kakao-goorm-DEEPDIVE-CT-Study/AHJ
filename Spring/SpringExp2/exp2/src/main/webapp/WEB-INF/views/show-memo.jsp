<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Show Memo</title>
</head>
<body>
<h1>Memo Details</h1>
<c:choose>
  <c:when test="${memo != null}">
    <p><strong>Title:</strong> ${memo.title}</p>
    <p><strong>Content:</strong> ${memo.content}</p>
    <p><strong>Writer:</strong> ${memo.writer}</p>
    <p><strong>Write Date:</strong> ${memo.writeDate}</p>
  </c:when>
  <c:otherwise>
    <p>No memo found</p>
  </c:otherwise>
</c:choose>
<form action="/memo" method="get">
  <button type="submit">Back to List</button>
</form>
</body>
</html>

