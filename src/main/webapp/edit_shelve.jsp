<%--
  Created by IntelliJ IDEA.
  User: Randeesha
  Date: 28-May-24
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Shelve Details</title>
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
            <div class="p-10 xs:p-0 mx-auto md:w-full md:max-w-md">
                <div class="text-center mb-6">
                    <h2 class="text-base font-semibold tracking-wider text-indigo-600 uppercase">Update Shelve</h2>
                    <p class="mt-2 text-3xl font-extrabold text-gray-900 sm:text-4xl">Update Shelve</p>
                </div>

                <form action="edit_shelve" method="POST">
                    <div class="bg-white shadow w-full rounded-lg divide-y divide-gray-200">
                        <div class="px-5 pt-6 pb-5">

                            <% HashMap<String, Object> shelve = (HashMap<String, Object>) request.getAttribute("shelves"); %>

                            <input class="hidden"
                                      type="text"
                                      value="<%= shelve.get("id") %>"
                                      name="id" id="id" />

                            <label class="font-semibold text-sm text-gray-600 pb-1 block">Quantity</label>
                            <input type="text"
                                   value="<%= shelve.get("quantity") %>"
                                   name="quantity" id="quantity" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />

                            <button type="submit" class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                                <span class="inline-block mr-2">Update Shelve</span>
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
                                </svg>
                            </button>
                            <div class="py-2">
                                <a href="update_shelve"
                                   class="transition duration-200 bg-red-400 hover:bg-red-600 focus:bg-red-700 focus:shadow-sm focus:ring-4 focus:ring-red-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                                    <span class="inline-block mr-2">Back</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
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
