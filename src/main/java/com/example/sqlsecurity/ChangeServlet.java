package com.example.sqlsecurity;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "ChangeServlet", value = "/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("user");
        String pass = request.getParameter("password");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");
        try {
            String hash = DB_API.getHash(name);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            if(!argon2.verify(hash, pass.toCharArray())){
                request.setAttribute("error","Wrong password!");
                request.getRequestDispatcher("change.jsp").forward(request,response);
            }
            if(!Objects.equals(pass1, pass2)){
                request.setAttribute("error","Passwords in the fields differ!");
                request.getRequestDispatcher("change.jsp").forward(request,response);
            }
            hash = argon2.hash(2,15*1024,1, pass1.toCharArray());
            DB_API.updateUser(name,hash);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("index.jsp");
    }
}
