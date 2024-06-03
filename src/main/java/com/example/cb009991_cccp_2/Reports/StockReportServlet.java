package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import facade.StoreManagementFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "StockReportServlet", value = "/stock_report")
public class StockReportServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            List<Map<String, Object>> stockReport = facade.generateStockReport();
            request.setAttribute("stockReport", stockReport);
            request.getRequestDispatcher("stock_report.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}