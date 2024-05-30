package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;

@WebServlet(name = "EditProductServlet", value = "/edit_product")
public class EditProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get product details from request parameters
        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        // Display the list of products
        Product product = new Product();

        // Get the product with the given ID
        HashMap<String, Object> productToUpdate = product.get(id);

        // This updates the existing product
        if (productToUpdate != null) {
            productToUpdate.put("name", name);
            productToUpdate.put("price", price);

            // saves the updated product
            product.update(id, productToUpdate);
        }

        response.sendRedirect("products.jsp");
    }
}