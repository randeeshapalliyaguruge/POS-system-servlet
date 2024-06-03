package com.example.cb009991_cccp_2.Shelves;

import java.io.*;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import facade.StoreManagementFacade;

@WebServlet(name = "ViewShelvesServlet", value = "/view_shelves")
public class ViewShelvesServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<HashMap<String, Object>> shelves = facade.viewAllShelves();
        request.setAttribute("shelves", shelves);
        request.getRequestDispatcher("view_shelves.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
