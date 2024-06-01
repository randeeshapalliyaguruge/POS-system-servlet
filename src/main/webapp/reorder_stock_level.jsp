<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Randeesha
  Date: 01-Jun-24
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reorder Stock</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div id="app">
    <div class="bg-white">
        <div class="relative bg-gray-900">
            <header class="relative z-10">
                <nav class="bg-gray-900 shadow">
                    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
                        <div class="relative flex h-16 justify-between">
                            <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                                <div class="flex flex-shrink-0 items-center">
                                    <a href="/">
                                        <img class="block h-32 w-auto lg:hidden mt-2" src="logo.png" alt="Logo">
                                    </a>
                                    <a href="/">
                                        <img class="hidden h-32 w-auto lg:block mt-2" src="logo.png" alt="Logo">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </header>
        </div>

        <!-- component -->
        <div class="min-h-screen bg-gray-100 flex flex-col pt-4">
            <div class="px-4 sm:px-6 lg:px-8">
                <div class="p-12 px-16">
                    <div class="sm:flex sm:items-center">
                        <div class="sm:flex-auto">
                            <h1 class="text-base font-semibold leading-6 text-gray-900">Stocks under 50 items</h1>
                            <p class="my-2 text-sm text-gray-700">A list of stock level which is under 50 products in the stock, wth their Product id, Product name and remaining Quantity.</p>
                            <a href="reports">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 19.5 3 12m0 0 7.5-7.5M3 12h18" />
                                </svg>
                            </a>
                        </div>
                    </div>
                    <div class="mt-8 flow-root">
                        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div class="inline-block min-w-full align-middle sm:px-6 lg:px-8">
                                <table class="min-w-full divide-y divide-gray-300">
                                    <thead>
                                    <tr class="divide-x divide-gray-200">
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-90">Product ID</th>
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Product Name</th>
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Quantity</th>
                                    </tr>
                                    </thead>
                                    <tbody class="divide-y divide-gray-300 bg-white">

                                        <%
                                            ResultSet resultSet = (ResultSet) request.getAttribute("reorderStockLevel");
                                            try {
                                                while (resultSet.next()) {
                                        %>

                                            <tr class="divide-x divide-gray-200">
                                                <td class="whitespace-nowrap  p-4 text-sm font-medium text-gray-900"><%=resultSet.getInt("id")%></td>
                                                <td class="whitespace-nowrap p-4 text-sm text-gray-500"><%=resultSet.getString("name")%></td>
                                                <td class="whitespace-nowrap p-4 text-sm text-gray-500"><%=resultSet.getInt("quantity")%></td>
                                            </tr>

                                        <%
                                                }

                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        %>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer aria-labelledby="footer-heading" class="bg-gray-900">
            <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
                <div class="border-t border-gray-800 py-10">
                    <div class="flex justify-center">
                        <p class="text-sm text-gray-400">Randeesha Palliyguruge (CB009991) - CCCP-2 ASSIGNMENT</p>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>

</body>
</html>
