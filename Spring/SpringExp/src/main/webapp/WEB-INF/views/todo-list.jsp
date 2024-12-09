<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.exp5.model.Todo" %>
<!DOCTYPE html>
<html>
<head>
    <title>TODO List</title>
</head>
<body>
<h1>TODO List</h1>
<ul>
    <%
        List<Todo> todos = (List<Todo>) request.getAttribute("todos");
        if (todos != null) {
            for (Todo todo : todos) {
    %>
    <li>
        <strong>Name:</strong> <%= todo.getName() %><br/>
        <strong>Task:</strong> <%= todo.getTask() %><br/>
        <strong>Start Date:</strong> <%= todo.getStartDate() %><br/>
        <strong>End Date:</strong> <%= todo.getEndDate() %><br/>
        <strong>Status:</strong>
        <%= todo.isComplete() ? "<span style='color: green;'>Completed</span>" : "<span style='color: red;'>Incomplete</span>" %>
        <br/><hr/>
    </li>
    <%
            }
        }
    %>
</ul>
<a href="/todo/add" style="text-decoration: none; padding: 10px 20px; background-color: #007bff; color: white; border-radius: 5px;">
    Add New TODO
</a>
</body>
</html>
