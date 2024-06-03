package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "ViewProducts", value = "/view_products")
public class ViewProducts extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ResultSet products = facade.viewAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("view_products.jsp").forward(request, response);
    }
}
