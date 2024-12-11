<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reserve Seat</title>
</head>
<body>
<h1>자리 예약</h1>

<p>예약 가능한 자리 번호:</p>
<form action="/reserve" method="post">
    <button type="submit" name="seatNum" value="1">1번</button>
    <button type="submit" name="seatNum" value="2">2번</button>
    <button type="submit" name="seatNum" value="3">3번</button>
    <button type="submit" name="seatNum" value="4">4번</button>
    <button type="submit" name="seatNum" value="5">5번</button>
</form>

</body>
</html>
