package com.example.cb009991_cccp_2.Stocks;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import models.Stock;

@WebServlet(name = "AddStockServlet", value = "/add_stock")
public class AddStockServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_stock.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();

        // Get stock details from request parameters
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String purchaseDate = request.getParameter("purchase_date");
        String expiryDate = request.getParameter("expire_date");

        HashMap<String, Object> productData = product.get(productId);
        if (productData != null) {

            Stock stock = new Stock();

            // Create a HashMap to store stock details
            HashMap<String, Object> data = new HashMap<>();
            data.put("product_id", String.valueOf(productId));
            data.put("quantity", String.valueOf(quantity));
            data.put("purchase_date", purchaseDate);
            data.put("expire_date", expiryDate);

            // Create a Stock instance and call the create method
            HashMap<String, Object> stockModel = stock.create(data);

            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Stock added successfully:</h1>");
            out.println("<p>Product ID: " + stockModel.get("product_id") + "</p>");
            out.println("<p>Quantity: " + stockModel.get("quantity") + "</p>");
            out.println("<p>Purchase Date: " + stockModel.get("purchase_date") + "</p>");
            out.println("<p>Expiry Date: " + stockModel.get("expire_date") + "</p>");
            out.println("</body></html>");

        }
        else {
            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Product not found</h1>");
            out.println("</body></html>");
        }
    }
}