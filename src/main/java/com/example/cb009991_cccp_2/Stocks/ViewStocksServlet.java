package com.example.cb009991_cccp_2.Stocks;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Stock;

@WebServlet(name = "ViewStocksServlet", value = "/view_stocks")
public class ViewStocksServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Stock stock = new Stock();

        request.setAttribute("stocks", stock.allStock());
        request.getRequestDispatcher("view_stocks.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}