package com.example;

import com.example.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { 
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
       
        try {
            // Utwórz połączenie z bazą danych
       
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://db01:3306/users", "app", "app123");


            // Utwórz i wykonaj zapytanie SQL
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            // Utwórz listę użytkowników
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                users.add(new User(id, name, lastName));
            }


            // Przekaż listę użytkowników do strony JSP
            request.setAttribute("users", users);




            RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
            dispatcher.forward(request, response);

            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } 
        catch(ClassNotFoundException e){
            e.printStackTrace();

        }finally {
            // Zamknij połączenie i inne zasoby
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}