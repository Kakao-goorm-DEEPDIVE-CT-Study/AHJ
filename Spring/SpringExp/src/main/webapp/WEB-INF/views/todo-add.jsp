<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add TODO</title>
</head>
<body>
<h1>Add a New TODO</h1>
<form action="/todo/add" method="post">
  <label for="name">Name:</label>
  <input type="text" name="name" id="name" required /><br/>

  <label for="task">Task:</label>
  <input type="text" name="task" id="task" required /><br/>

  <label for="startDate">Start Date:</label>
  <input type="datetime-local" name="startDate" id="startDate" required /><br/>

  <label for="endDate">End Date:</label>
  <input type="datetime-local" name="endDate" id="endDate" required /><br/>

  <label for="complete">Completed:</label>
  <input type="checkbox" name="complete" id="complete" /><br/>

  <button type="submit">Add Task</button>
</form>

<a href="/todo">
  <button type="button">Back to List</button>
</a>
</body>
</html>
