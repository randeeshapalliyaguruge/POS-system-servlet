package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "UpdateShelveServlet", value = "/update_shelve")
public class UpdateShelveServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("update_shelve.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("shelve_id"));
        HashMap<String, Object> shelveData = facade.getShelve(id);

        request.setAttribute("shelve", shelveData);
        request.getRequestDispatcher("edit_shelve.jsp").forward(request, response);
    }
}
