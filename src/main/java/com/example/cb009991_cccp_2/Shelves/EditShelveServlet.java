package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "EditShelveServlet", value = "/edit_shelve")
public class EditShelveServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("shelve_id"));
        HashMap<String, Object> shelveData = facade.getShelve(id);

        if (shelveData != null) {
            request.setAttribute("shelve", shelveData);
            request.getRequestDispatcher("edit_shelve.jsp").forward(request, response);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve not found</h1>");
            out.println("</body></html>");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        HashMap<String, Object> updatedShelve = facade.updateShelve(id, newQuantity);

        if (updatedShelve != null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve updated successfully:</h1>");
            out.println("<p>Shelve ID: " + id + "</p>");
            out.println("<p>New Quantity: " + updatedShelve.get("quantity") + "</p>");
            out.println("</body></html>");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Shelve not found</h1>");
            out.println("<p>Shelve ID: " + id + "</p>");
            out.println("</body></html>");
        }
    }
}
