package com.example.sqlsecurity;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "SigninServlet", value = "/signin")
public class SigninServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");
        if(!Objects.equals(pass1, pass2)){
            request.setAttribute("error","Passwords in the fields differ!");
            request.getRequestDispatcher("signin.jsp").forward(request,response);
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
        String hash = argon2.hash(2,15*1024,1, pass1.toCharArray());
        try {
            if(!DB_API.setUser(name,hash)){
                request.setAttribute("error","Username already exists!");
                request.getRequestDispatcher("signin.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",name);
        response.sendRedirect("index.jsp");
    }
}
