<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Randeesha
  Date: 28-May-24
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Product</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div id="app">
    <div class="bg-white">
        <div class="relative bg-gray-900">
            <header class="relative z-10">
                <nav class="bg-white shadow">
                    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
                        <div class="relative flex h-16 justify-between">
                            <div class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                                <div class="flex flex-shrink-0 items-center">
                                    <a href="/">
                                        <img class="block h-8 w-auto lg:hidden" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company">
                                    </a>
                                    <a href="/">
                                        <img class="hidden h-8 w-auto lg:block" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company">
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
                    <h2 class="text-base font-semibold tracking-wider text-indigo-600 uppercase">Update Product</h2>
                    <p class="mt-2 text-3xl font-extrabold text-gray-900 sm:text-4xl">Update Product</p>
                </div>

                <!--
                    todo: send to a new edit product servlet with a dopost function to get the existing product details with the passed id from the form,
                    todo: then, get the new updated data from the form
                    todo: update it with the new data
                    todo: save
                    todo: return to the products.jsp page
                 -->

                <form action="#" method="POST">



                    <div class="bg-white shadow w-full rounded-lg divide-y divide-gray-200">
                        <div class="px-5 py-7">

                            <% HashMap<String, Object> product = (HashMap<String, Object>) request.getAttribute("product"); %>

                            <input class="hidden"
                                      type="text"
                                      value="<%= product.get("id") %>"
                                      name="id" id="id" />

                            <label class="font-semibold text-sm text-gray-600 pb-1 block">Product Name</label>
                            <input type="text"
                                   value="<%= product.get("name") %>"
                                   name="name" id="name" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />

                            <label class="font-semibold text-sm text-gray-600 pb-1 block">Item Price</label>
                            <input type="text"
                                   value="<%= product.get("price") %>"
                                   name="price" id="price" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />

                            <button type="submit" class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                                <span class="inline-block mr-2">Update Product</span>
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
                                </svg>
                            </button>
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
