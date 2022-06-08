package com.example.sqlsecurity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "TransportsServlet", value = "/transports")
public class TransportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(request.getParameter("page"));
            int pagesize = Integer.parseInt(request.getParameter("pagesize"));
            request.setAttribute("page",page);
            request.setAttribute("pagesize",pagesize);
            request.setAttribute("transportList",DB_API.readTransports(page,pagesize));
            request.setAttribute("transport_idList",DB_API.getTransportIds());
            request.setAttribute("owner_idList",DB_API.getPersonIds());
            request.setAttribute("license_plateList",DB_API.getPlates());
            request.setAttribute("brandList",DB_API.getBrands());
            request.setAttribute("colorList",DB_API.getColors());
            request.getRequestDispatcher("transports.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            switch (request.getParameter("purpose")){
                case "C":
                    if(!DB_API.insertTransport(
                            new BigDecimal(request.getParameter("transport_id")),
                            new BigDecimal(request.getParameter("owner_id")),
                            request.getParameter("license_plate"),
                            request.getParameter("brand"),
                            request.getParameter("model"),
                            new BigDecimal(request.getParameter("model_year")),
                            request.getParameter("color")
                    )){
                        request.setAttribute("error","Transport_id already exists");
                        request.getRequestDispatcher("transports.jsp").forward(request,response);
                    }
                    break;
                case "U":
                    DB_API.updateTransport(new BigDecimal(request.getParameter("transport_id")),new BigDecimal(request.getParameter("owner_id")));
                    break;
                case "D":
                    DB_API.deleteTransport(new BigDecimal(request.getParameter("transport_id")));
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("transports?page=1&pagesize=10&purpose=R");
    }
}
