package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "AddShelveServlet", value = "/add_shelve")
public class AddShelveServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_shelve.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HashMap<String, Object> productData = facade.getProduct(productId);
        if (productData != null) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("product_id", productId);
            data.put("quantity", quantity);

            HashMap<String, Object> shelveModel = facade.addShelve(data);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve added successfully:</h1>");
            out.println("<p>Product ID: " + shelveModel.get("product_id") + "</p>");
            out.println("<p>Stock ID: " + shelveModel.get("stock_id") + "</p>");
            out.println("<p>Quantity: " + shelveModel.get("quantity") + "</p>");
            out.println("</body></html>");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Product not found</h1>");
            out.println("</body></html>");
        }
    }
}
