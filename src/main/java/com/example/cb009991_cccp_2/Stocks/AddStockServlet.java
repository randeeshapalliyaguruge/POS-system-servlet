package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "AddStockServlet", value = "/add_stock")
public class AddStockServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_stock.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String purchaseDate = request.getParameter("purchase_date");
        String expiryDate = request.getParameter("expire_date");

        facade.addStock(productId, quantity, purchaseDate, expiryDate);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Stock added successfully:</h1>");
        out.println("<p>Product ID: " + productId + "</p>");
        out.println("<p>Quantity: " + quantity + "</p>");
        out.println("<p>Purchase Date: " + purchaseDate + "</p>");
        out.println("<p>Expiry Date: " + expiryDate + "</p>");
        out.println("</body></html>");
    }
}
