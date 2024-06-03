package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "UpdateProductServlet", value = "/update_product")
public class UpdateProductServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("update_product.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("product_id"));
        HashMap<String, Object> productToUpdate = facade.getProduct(id);
        request.setAttribute("product", productToUpdate);
        request.getRequestDispatcher("edit_product.jsp").forward(request, response);
    }
}
