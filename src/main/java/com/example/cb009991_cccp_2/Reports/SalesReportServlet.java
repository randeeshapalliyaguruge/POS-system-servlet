package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import facade.StoreManagementFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SalesReportServlet", value = "/sales_report")
public class SalesReportServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");

        try {
            List<Map<String, Object>> salesData = facade.generateSalesReport(date);
            request.setAttribute("salesData", salesData);
            request.getRequestDispatcher("sales_report.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}