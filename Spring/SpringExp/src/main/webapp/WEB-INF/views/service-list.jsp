<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <title>Service List</title>
</head>
<body>
<h1>서비스 리스트</h1>

<!-- 예약 결과 출력 -->
<c:if test="${not empty result}">
  <p>예약 결과: ${result}</p>
</c:if>

<!-- 사용자 정보 출력 -->
<c:if test="${not empty user}">
  <p>현재 사용자: ${user.name}</p>
  <p>현재 예약 좌석:</p>
  <ul>
    <c:forEach var="seat" items="${user.seats}">
      <li>${seat}</li>
    </c:forEach>
  </ul>
</c:if>

<!-- 버튼 -->
<form action="/reserve" method="get" style="display: inline">
  <button type="submit">자리 예약</button>
</form>
<form action="/use" method="get" style="display: inline">
  <button type="submit">자리 사용</button>
</form>
<form action="/exit" method="post" style="display: inline">
  <button type="submit">종료</button>
</form>
<!-- 새 버튼: 아이디 생성 -->
<form action="/generate" method="post" style="display: inline">
  <button type="submit">아이디 생성</button>
</form>

</body>
</html>
