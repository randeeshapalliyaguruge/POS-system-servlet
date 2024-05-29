package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;

@WebServlet(name = "UpdateProductServlet", value = "/update_product")
public class UpdateProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("update_product.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get the form data named product_id
        int id = Integer.parseInt(request.getParameter("product_id"));

        // Display the list of products
        Product product = new Product();

        // Get the product with the given ID
        HashMap<String, Object> productToUpdate = product.get(id);

        request.setAttribute("product", productToUpdate);
        request.getRequestDispatcher("edit_product.jsp").forward(request, response);
    }

    public void destroy() {
    }
}