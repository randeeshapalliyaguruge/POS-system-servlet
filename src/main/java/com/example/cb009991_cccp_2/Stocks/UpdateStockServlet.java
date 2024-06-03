package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "UpdateStockServlet", value = "/update_stock")
public class UpdateStockServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("update_stock.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("stock_id"));
        HashMap<String, Object> stockToUpdate = facade.getStock(id);
        request.setAttribute("stock", stockToUpdate);
        request.getRequestDispatcher("edit_stock.jsp").forward(request, response);
    }
}
