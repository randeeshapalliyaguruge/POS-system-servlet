package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "ViewStocksServlet", value = "/view_stocks")
public class ViewStocksServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<HashMap<String, Object>> stocks = facade.viewAllStocks();
        request.setAttribute("stocks", stocks);
        request.getRequestDispatcher("view_stocks.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
