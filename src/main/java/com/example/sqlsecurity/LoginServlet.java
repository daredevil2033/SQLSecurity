package com.example.sqlsecurity;



import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        try {
            String hash = DB_API.getHash(name);
            if (hash == null) {
                request.setAttribute("error","User doesn't exist!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            if(!argon2.verify(hash, pass.toCharArray())){
                request.setAttribute("error","Invalid username or password!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",name);
        response.sendRedirect("index.jsp");
    }
}
