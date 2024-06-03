package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "EditStockServlet", value = "/edit_stock")
public class EditStockServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String purchaseDate = request.getParameter("purchase_date");
        String expiryDate = request.getParameter("expire_date");
        facade.updateStock(id, quantity, purchaseDate, expiryDate);
        response.sendRedirect("stocks.jsp");
    }
}
