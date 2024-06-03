package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "DeleteShelveServlet", value = "/delete_shelve")
public class DeleteShelveServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("delete_shelve.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("shelve_id"));
        facade.deleteShelve(id);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Shelve deleted successfully:</h1>");
        out.println("<p>Shelve ID: " + id + "</p>");
        out.println("</body></html>");
    }
}
