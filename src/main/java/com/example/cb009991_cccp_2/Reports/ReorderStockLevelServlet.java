package com.example.cb009991_cccp_2.Reports;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import facade.StoreManagementFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ReorderStockLevelServlet", value = "/reorder_stock_level")
public class ReorderStockLevelServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            List<Map<String, Object>> reorderStockLevel = facade.generateReorderReport();
            request.setAttribute("reorderStockLevel", reorderStockLevel);
            request.getRequestDispatcher("reorder_stock_level.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}