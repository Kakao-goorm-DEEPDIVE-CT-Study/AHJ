<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Use Seat</title>
</head>
<body>
<h1>자리 사용</h1>

<!-- 예약된 좌석 번호 리스트 출력 -->
<c:if test="${not empty user.seats}">
  <form action="/use" method="post">
    <p>예약된 좌석 목록:</p>
    <c:forEach var="seat" items="${user.seats}" varStatus="status">
      <label>
        <!-- 라디오 버튼으로 좌석 선택 -->
        <input type="radio" name="choice" value="${status.index}" required>
        좌석 번호: ${seat}
      </label>
      <br>
    </c:forEach>
    <button type="submit">선택한 자리 사용</button>
  </form>
</c:if>

<!-- 예약된 좌석이 없을 때 메시지 표시 -->
<c:if test="${empty user.seats}">
  <p>예약된 좌석이 없습니다. 먼저 좌석을 예약해주세요.</p>
  <form action="/reserve" method="get">
    <button type="submit">자리 예약</button>
  </form>
</c:if>

</body>
</html>
