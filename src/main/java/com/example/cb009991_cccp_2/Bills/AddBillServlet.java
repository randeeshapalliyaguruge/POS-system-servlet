package com.example.cb009991_cccp_2.Bills;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Bill;
import models.Database;
import models.Product;
import jakarta.servlet.RequestDispatcher;

@WebServlet(name = "AddBillServlet", value = "/add_bill")
public class AddBillServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("num_of_items", 0);
        request.setAttribute("completed", false);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add_bill.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num_of_items = Integer.parseInt(request.getParameter("num_of_items"));
        boolean completed = Boolean.parseBoolean(request.getParameter("completed"));

        // Retrieve the first_request parameter correctly
        boolean first_request = Boolean.parseBoolean(request.getParameter("first_request"));

        if (completed) {

            double totalBill = Double.parseDouble(request.getParameter("totalBill"));

            System.out.println("totalBill: " + totalBill);

            double discount = Double.parseDouble(request.getParameter("discount"));
            double cash_tendered = Double.parseDouble(request.getParameter("cash_tendered"));

            double discountAmount = (totalBill * discount) / 100;
            totalBill -= discountAmount;

            double change = cash_tendered - totalBill;

            Bill bill = new Bill();
            HashMap<String, Object> billData = new HashMap<>();
            billData.put("total_price", totalBill);
            billData.put("cash_tendered", cash_tendered);
            billData.put("change", change);
            billData.put("discount", discountAmount);
            billData.put("created_date", java.time.LocalDate.now().toString());
            bill.create(billData);

            response.setContentType("text/html");

            request.setAttribute("billData", billData);
            request.getRequestDispatcher("add_bill.jsp").forward(request, response);

        } else {

            if (first_request) {

                response.setContentType("text/html");
                request.setAttribute("num_of_items", num_of_items);
                request.setAttribute("completed", completed);

                request.getRequestDispatcher("add_bill.jsp").forward(request, response);

            } else if (num_of_items > 0) {

                int product_id = Integer.parseInt(request.getParameter("product_id"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Product product = new Product();
                HashMap<String, Object> productData = product.get(product_id);

                if (productData != null) {
                    Bill.generateBill(productData, quantity);

                    // get the request parameter double called total_bill
                    double totalBill = Double.parseDouble(request.getParameter("totalBill"));

                    double price = Double.parseDouble(productData.get("price").toString());

                    totalBill += price * quantity;

                    num_of_items -= 1;

                    completed = num_of_items == 0;

                    response.setContentType("text/html");
                    request.setAttribute("num_of_items", num_of_items);
                    request.setAttribute("completed", completed);

                    // sends the total bill to the jsp
                    request.setAttribute("totalBill", totalBill);

                    request.getRequestDispatcher("add_bill.jsp").forward(request, response);

                } else {
                    response.setContentType("text/html");
                    request.setAttribute("product_not_found", "Product with ID " + product_id + " does not exist.");
                    request.getRequestDispatcher("add_bill.jsp").forward(request, response);
                }
            }
        }
    }
}