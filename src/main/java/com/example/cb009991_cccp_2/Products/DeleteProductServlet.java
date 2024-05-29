package com.example.cb009991_cccp_2.Products;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;

@WebServlet(name = "DeleteProductServlet", value = "/delete_product")
public class DeleteProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("delete_product.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //delete the product with the given ID
        int id = Integer.parseInt(request.getParameter("product_id"));
        Product product = new Product();
        product.delete(id);

        response.sendRedirect("products.jsp");
    }

    public void destroy() {
    }
}