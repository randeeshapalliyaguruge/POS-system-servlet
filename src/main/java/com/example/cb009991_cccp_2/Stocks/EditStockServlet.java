package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Stock;

@WebServlet(name = "EditStockServlet", value = "/edit_stock")
public class EditStockServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get stock details from request parameters
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String purchaseDate = request.getParameter("purchase_date");
        String expiryDate = request.getParameter("expire_date");

        // Display the list of stocks
        Stock stock = new Stock();

        // Get the stock with the given ID
        HashMap<String, Object> stockToUpdate = stock.get(id);

        // This updates the existing stock
        if (stockToUpdate != null) {
            stockToUpdate.put("quantity", quantity);
            stockToUpdate.put("purchase_date", purchaseDate);
            stockToUpdate.put("expire_date", expiryDate);

            // saves the updated stock
            stock.update(id, stockToUpdate);
        }

        response.sendRedirect("stocks.jsp");
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
    }
}