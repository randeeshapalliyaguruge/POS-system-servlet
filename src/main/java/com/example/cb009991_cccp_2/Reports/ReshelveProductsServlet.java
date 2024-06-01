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

@WebServlet(name = "ReshelveProductsServlet", value = "/reshelve_products")
public class ReshelveProductsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportGenerator reportGenerator = new ReportGenerator();
        try {
            List<Map<String, Object>> reshelveData = reportGenerator.generateReshelvingReport();
            if (reshelveData == null) {
                reshelveData = new ArrayList<>(); // Ensure reshelveData is not null
            }
            request.setAttribute("reshelveData", reshelveData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reshelve_products.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}