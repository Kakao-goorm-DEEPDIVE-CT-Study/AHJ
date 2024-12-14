<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Memo List</title>
</head>
<body>
<h1>Memo List</h1>

<form action="/show" method="get" id="memoForm">
  <table border="1" style="width: 100%; text-align: center;">
    <tr>
      <td colspan="3">
        <c:choose>
          <c:when test="${not empty memoList}">
            <table border="0" style="width: 100%; text-align: left;">
              <c:forEach var="memo" items="${memoList}" varStatus="status">
                <tr>
                  <td>
                    <input type="radio" name="choice" value="${status.index}" required />
                      ${status.index + 1}. ${memo.title}
                  </td>
                </tr>
              </c:forEach>
            </table>
          </c:when>
          <c:otherwise>
            <div style="text-align: center; padding: 10px; font-style: italic;">
              No memos available.
            </div>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>

  <div style="margin-top: 20px; text-align: center;">
    <button type="submit" style="width: 100px; height: 40px; margin: 5px;">View</button>
  </div>
</form>
<form action="/edit" method="get" style="display:inline;">
  <input type="hidden" name="index" id="selectedIndex" />
  <button type="submit" style="width: 100px; height: 40px; margin: 5px;">Edit</button>
</form>
<form action="/remove" method="post" style="display:inline;">
  <input type="hidden" name="index" id="selectedRemoveIndex" />
  <button type="submit" style="width: 100px; height: 40px; margin: 5px;">Delete</button>
</form>
<form action="/add" method="get" style="display:inline;">
  <button type="submit" style="width: 100px; height: 40px; margin: 5px;">Add</button>
</form>
</div>

<script>
  // 선택한 라디오 버튼의 값을 Edit 및 Delete 버튼에 반영
  document.getElementById('memoForm').addEventListener('change', function () {
    const selected = document.querySelector('input[name="choice"]:checked');
    if (selected) {
      document.getElementById('selectedIndex').value = selected.value;
      document.getElementById('selectedRemoveIndex').value = selected.value;
    }
  });
</script>

</body>
</html>
