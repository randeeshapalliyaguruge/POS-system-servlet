package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.ReportGenerator;

@WebServlet(name = "SalesReportServlet", value = "/sales_report")
public class SalesReportServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        ReportGenerator report = new ReportGenerator();
        try {
            List<Map<String, Object>> salesData = report.generateSalesReport(date);
            // Ensure salesData is not null
            if (salesData == null) {
                salesData = new ArrayList<>();
            }
            request.setAttribute("salesData", salesData);
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("sales_report.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}