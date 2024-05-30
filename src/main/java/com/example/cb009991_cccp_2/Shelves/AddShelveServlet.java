package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;
import models.Shelve;
import models.Stock;


@WebServlet(name = "AddShelveServlet", value = "/add_shelve")
public class AddShelveServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_shelve.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product product = new Product();
        HashMap<String, Object> productData = product.get(productId);
        if (productData != null) {
            Stock stock = new Stock();
            List<HashMap<String, Object>> stocks = stock.getAllByProductId(productId);
            stocks.sort(Comparator.comparing((HashMap<String, Object> o) -> (String) o.get("expire_date"))
                    .thenComparing(o -> (String) o.get("purchase_date")));

            for (HashMap<String, Object> stockData : stocks) {
                int stockQuantity = (int) stockData.get("quantity");
                if (stockQuantity >= quantity) {
                    stockData.put("quantity", stockQuantity - quantity);
                    stock.update((int) stockData.get("id"), stockData);

                    Shelve shelve = new Shelve();
                    HashMap<String, Object> shelveData = new HashMap<>();
                    shelveData.put("stock_id", stockData.get("id"));
                    shelveData.put("product_id", productData.get("id"));
                    shelveData.put("quantity", quantity);
                    HashMap<String, Object> shelveModel = shelve.create(shelveData);

                    // Send response back to client
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h1>Shelve added successfully:</h1>");
                    out.println("<p>Product ID: " + shelveModel.get("product_id") + "</p>");
                    out.println("<p>Stock ID: " + shelveModel.get("stock_id") + "</p>");
                    out.println("<p>Quantity: " + shelveModel.get("quantity") + "</p>");
                    out.println("</body></html>");

                    break;
                } else {
                    stockData.put("quantity", 0);
                    stock.update((int) stockData.get("id"), stockData);

                    if (stockQuantity > 0) {
                        Shelve shelve = new Shelve();
                        HashMap<String, Object> shelveData = new HashMap<>();
                        shelveData.put("stock_id", stockData.get("id"));
                        shelveData.put("product_id", productData.get("id"));
                        shelveData.put("quantity", stockQuantity);
                        HashMap<String, Object> shelveModel = shelve.create(shelveData);

                        // Send response back to client
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                        out.println("<html><body>");
                        out.println("<h1>Shelve added successfully:</h1>");
                        out.println("<p>Product ID: " + shelveModel.get("product_id") + "</p>");
                        out.println("<p>Stock ID: " + shelveModel.get("stock_id") + "</p>");
                        out.println("<p>Quantity: " + shelveModel.get("quantity") + "</p>");
                        out.println("</body></html>");

                    }
                    quantity -= stockQuantity;
                }
            }
        } else {
            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Product not found</h1>");
            out.println("</body></html>");
        }
    }
}