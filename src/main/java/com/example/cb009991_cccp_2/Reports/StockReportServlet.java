package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.ResultSet;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Stock;

@WebServlet(name = "StockReportServlet", value = "/stock_report")
public class StockReportServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Stock stock = new Stock();
        ResultSet rs = stock.allStock();
        HashMap<Integer, String> productNames = new HashMap<>();

        try {
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                if (!productNames.containsKey(productId)) {
                    productNames.put(productId, stock.getProductName(productId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("stocks", stock.allStock());
        request.setAttribute("productNames", productNames);
        request.getRequestDispatcher("stock_report.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}