<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.model.User" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
    <h1>Users</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Lastname</th>
        </tr>

       
        <% 
       
        // Odebranie listy użytkowników przekazanej jako atrybut "users1"
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        if (users != null) {
            // Jeśli lista użytkowników nie jest pusta, iteruj po niej
            for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getLastname() %></td>
    </tr>
    <% 
                }
            } else {
                // Obsługa przypadku, gdy lista użytkowników jest pusta
                out.println("<tr><td colspan=\"3\">No users found</td></tr>");
            }
        %>

    </table>
</body>
</html>
