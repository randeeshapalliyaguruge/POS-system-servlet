package com.example.cb009991_cccp_2.Reports;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Bill;

@WebServlet(name = "BillReportServlet", value = "/bill_report")
public class BillReportServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Bill bill = new Bill();
        request.setAttribute("bills", bill.all());
        request.getRequestDispatcher("bill_report.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}