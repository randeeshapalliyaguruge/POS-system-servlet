package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Shelve;
import models.Stock;

@WebServlet(name = "EditShelveServlet", value = "/edit_shelve")
public class EditShelveServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        Shelve shelve = new Shelve();
        Stock stock = new Stock();

        HashMap<String, Object> shelveToUpdate = shelve.get(id);
        if (shelveToUpdate != null) {
            int oldQuantity = (int) shelveToUpdate.get("quantity");
            int stockIdToUpdate = (int) shelveToUpdate.get("stock_id");
            HashMap<String, Object> stockDataToUpdate = stock.get(stockIdToUpdate);

            int stockQuantityToUpdate = (int) stockDataToUpdate.get("quantity");
            stockDataToUpdate.put("quantity", stockQuantityToUpdate + oldQuantity - newQuantity);
            stock.update(stockIdToUpdate, stockDataToUpdate);

            HashMap<String, Object> dataToUpdate = new HashMap<>();
            dataToUpdate.put("quantity", newQuantity);
            HashMap<String, Object> updatedShelve = shelve.update(id, dataToUpdate);

            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve updated successfully:</h1>");
            out.println("<p>Shelve ID: " + id + "</p>");
            out.println("<p>Old Quantity: " + oldQuantity + "</p>");
            out.println("<p>New Quantity: " + newQuantity + "</p>");
            out.println("</body></html>");

        } else {
            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve not found:</h1>");
            out.println("<p>Shelve ID: " + id + "</p>");
            out.println("</body></html>");
        }
    }
}