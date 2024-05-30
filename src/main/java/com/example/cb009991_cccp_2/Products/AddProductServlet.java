package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "AddProductServlet", value = "/add_product")
public class AddProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_product.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get product details from request parameters
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        // Create a HashMap to store product details
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("price", price);

        // Create an instance of ProductManager and call the addProduct method
        managers.ProductManager productManager = new managers.ProductManager();

        HashMap<String, Object> productModel = productManager.addProduct(data);

        // Send response back to client
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Product added successfully:</h1>");
        out.println("<p>Name: " + productModel.get("name") + "</p>");
        out.println("<p>Price: " + productModel.get("price") + "</p>");
        out.println("</body></html>");
    }
}