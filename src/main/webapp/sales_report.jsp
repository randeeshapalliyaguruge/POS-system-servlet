<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Randeesha
  Date: 01-Jun-24
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sales Report</title>
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
                            <h1 class="text-base font-semibold leading-6 text-gray-900">Sales Report</h1>
                            <p class="my-2 text-sm text-gray-700">Sales Report in the system: Total sale for a given day.</p>
                            <a href="reports">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 19.5 3 12m0 0 7.5-7.5M3 12h18" />
                                </svg>
                            </a>
                        </div>

                        <form action="sales_report" method="GET">
                            <div class="bg-white shadow w-full rounded-lg divide-y divide-gray-200">
                                <div class="px-5 pt-7 pb-5">
                                    <label class="font-semibold text-sm text-gray-600 pb-1 block">Enter the date (DD-MM-YYYY):</label>
                                    <input type="date" name="date" id="date" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />

                                    <button type="submit" class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                                        <span class="inline-block mr-2">Generate Report</span>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
                                        </svg>
                                    </button>
                                    <div class="py-2">
                                        <a href="reports"
                                           class="transition duration-200 bg-red-400 hover:bg-red-600 focus:bg-red-700 focus:shadow-sm focus:ring-4 focus:ring-red-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                                            <span class="inline-block mr-2">Back</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="mt-8 flow-root">
                        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div class="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
                                <table class="min-w-full divide-y divide-gray-300">
                                    <thead>
                                    <tr class="divide-x divide-gray-200">
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Product ID</th>
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Name</th>
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Total Quantity</th>
                                        <th scope="col" class="px-4 py-3.5 text-left text-sm font-semibold text-gray-900">Total Revenue</th>
                                    </tr>
                                    </thead>
                                    <tbody class="divide-y divide-gray-300 bg-white">

                                    <%
                                        List<Map<String, Object>> salesData = (List<Map<String, Object>>) request.getAttribute("salesData");
                                        if(salesData != null && !salesData.isEmpty()) {
                                            for (Map<String, Object> row : salesData) {
                                    %>

                                    <tr class="divide-x divide-gray-200">
                                        <td class="whitespace-nowrap  p-4 text-sm font-medium text-gray-900"><%= row.get("product_id") %></td>
                                        <td class="whitespace-nowrap p-4 text-sm text-gray-500"><%=row.get("name")%></td>
                                        <td class="whitespace-nowrap p-4 text-sm text-gray-500"><%=row.get("total_quantity")%></td>
                                        <td class="whitespace-nowrap p-4 text-sm text-gray-500"><%=row.get("total_revenue")%>/=</td>
                                    </tr>

                                    <%
                                        }
                                    } else {
                                    %>

                                    <tr>
                                        <td colspan="4" class="text-center text-lg font-semibold text-gray-900">No records available on the given date</td>
                                    </tr>

                                    <%
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
