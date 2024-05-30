package com.example.cb009991_cccp_2.Stocks;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Stock;

@WebServlet(name = "DeleteStockServlet", value = "/delete_stock")
public class DeleteStockServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("delete_stock.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //delete the stock with the given ID
        int id = Integer.parseInt(request.getParameter("stock_id"));
        Stock stock = new Stock();
        stock.delete(id);

        response.sendRedirect("stocks.jsp");
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void destroy() {
    }
}