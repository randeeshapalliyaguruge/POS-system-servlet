package com.example.cb009991_cccp_2.Shelves;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Shelve;

@WebServlet(name = "ViewShelvesServlet", value = "/view_shelves")
public class ViewShelvesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Shelve shelve = new Shelve();

        request.setAttribute("shelves", shelve.allShelves());
        request.getRequestDispatcher("view_shelves.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}