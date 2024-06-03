package com.example.cb009991_cccp_2.Products;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "EditProductServlet", value = "/edit_product")
public class EditProductServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        facade.updateProduct(id, name, price);
        response.sendRedirect("products.jsp");
    }
}
