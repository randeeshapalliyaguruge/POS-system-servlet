package com.example.cb009991_cccp_2.Reports;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.ReportGenerator;

@WebServlet(name = "ReorderStockLevelServlet", value = "/reorder_stock_level")
public class ReorderStockLevelServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ReportGenerator reportGenerator = new ReportGenerator();

        request.setAttribute("reorderStockLevel", reportGenerator.generateReorderReport());
        request.getRequestDispatcher("reorder_stock_level.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}