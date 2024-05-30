package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Stock;

@WebServlet(name = "UpdateStockServlet", value = "/update_stock")
public class UpdateStockServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("update_stock.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("stock_id"));

        Stock stock = new Stock();

        // Get the stock with the given ID
        HashMap<String, Object> stockToUpdate = stock.get(id);

        request.setAttribute("stock", stockToUpdate);
        request.getRequestDispatcher("edit_stock.jsp").forward(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
    }
}