package com.example.cb009991_cccp_2.Reports;

import java.io.*;

import facade.StoreManagementFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ReportsServlet", value = "/reports")
public class ReportsServlet extends HttpServlet {
    private StoreManagementFacade facade = new StoreManagementFacade();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect("reports.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}