package com.example.sqlsecurity;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if ((!request.getRequestURI().contains("login") || !request.getRequestURI().contains("signin"))
                && request.getSession(false) == null) {
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        chain.doFilter(request, response);
    }
}
