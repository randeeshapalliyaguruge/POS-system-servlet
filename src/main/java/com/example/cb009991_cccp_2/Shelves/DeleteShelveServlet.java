package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Shelve;
import models.Stock;

@WebServlet(name = "DeleteShelveServlet", value = "/delete_shelve")
public class DeleteShelveServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("delete_shelve.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idToDelete = Integer.parseInt(request.getParameter("shelve_id"));

        Shelve shelve = new Shelve();
        Stock stock = new Stock();

        HashMap<String, Object> shelveToDelete = shelve.get(idToDelete);
        if (shelveToDelete != null) {
            int quantityToDelete = (int) shelveToDelete.get("quantity");
            int stockIdToDelete = (int) shelveToDelete.get("stock_id");
            HashMap<String, Object> stockDataToDelete = stock.get(stockIdToDelete);

            int stockQuantityToDelete = (int) stockDataToDelete.get("quantity");
            stockDataToDelete.put("quantity", stockQuantityToDelete + quantityToDelete);
            stock.update(stockIdToDelete, stockDataToDelete);

            shelve.delete(idToDelete);

            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve deleted successfully:</h1>");
            out.println("<p>Shelve ID: " + idToDelete + "</p>");
            out.println("</body></html>");

        } else {
            // Send response back to client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve not found:</h1>");
            out.println("<p>Shelve ID: " + idToDelete + "</p>");
            out.println("</body></html>");
        }
    }
}