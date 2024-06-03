package com.example.cb009991_cccp_2.Products;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "AddProductServlet", value = "/add_product")
public class AddProductServlet extends HttpServlet {

    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("add_product.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        facade.addProduct(name, price);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Product added successfully:</h1>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Price: " + price + "</p>");
        out.println("</body></html>");
    }
}
