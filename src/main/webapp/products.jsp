<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Products</title>
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
          <h2 class="text-base font-bold tracking-wider text-indigo-600 uppercase">Synex Outlet Store (SYOS)</h2>
        </div>
        <div class="bg-white shadow w-full rounded-lg">
          <div class="px-5 py-2 pt-4">
            <a href="/AddProductServlet"
               class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
              <span class="inline-block mr-2">Add New Product</span>
            </a>
          </div>

          <div class="px-5 py-2">
            <a href="addtostock"
               class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
              <span class="inline-block mr-2">Update a Product</span>
            </a>
          </div>

          <div class="px-5 py-2">
            <a href="addtostock"
               class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
              <span class="inline-block mr-2">View All Products</span>
            </a>
          </div>

          <div class="px-5 py-2">
            <a href="takefromstock"
               class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
              <span class="inline-block mr-2">Delete a Product</span>
            </a>
          </div>

          <div class="px-5 py-2 pb-4">
            <a href="/"
               class="transition duration-200 bg-red-400 hover:bg-red-600 focus:bg-red-700 focus:shadow-sm focus:ring-4 focus:ring-red-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
              <span class="inline-block mr-2">Back</span>
            </a>
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