package com.example.sqlsecurity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "PersonsServlet", value = "/persons")
public class PersonsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            int pagesize = Integer.parseInt(request.getParameter("pagesize"));
            request.setAttribute("page",page);
            request.setAttribute("pagesize",pagesize);
            request.setAttribute("personList",DB_API.readPersons(page,pagesize));
            request.setAttribute("person_idList",DB_API.getPersonIds());
            request.getRequestDispatcher("persons.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            switch (request.getParameter("purpose")){
                case "C":
                    if(!DB_API.insertPerson(
                            new BigDecimal(request.getParameter("person_id")),
                            request.getParameter("name"),
                            request.getParameter("gender"),
                            request.getParameter("birthday"))){
                        request.setAttribute("error","Person_id already exists");
                        request.getRequestDispatcher("persons.jsp").forward(request,response);
                    }
                    break;
                case "U":
                    DB_API.updatePerson(new BigDecimal(request.getParameter("person_id")),request.getParameter("name"));
                    break;
                case "D":
                    DB_API.deletePerson(new BigDecimal(request.getParameter("person_id")));
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("persons?page=1&pagesize=10&purpose=R");
    }
}
