package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import facade.StoreManagementFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ReshelveProductsServlet", value = "/reshelve_products")
public class ReshelveProductsServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Map<String, Object>> reshelveData = facade.generateReshelvingReport();
            request.setAttribute("reshelveData", reshelveData);
            request.getRequestDispatcher("reshelve_products.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}