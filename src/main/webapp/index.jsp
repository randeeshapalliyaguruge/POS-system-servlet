<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>SYOS Menu - CB009991</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div id="app">
  <div class="bg-white">
    <div class="bg-gray-900">
      <header class="absolute inset-x-0 top-0 z-50">
        <nav class="flex items-center justify-between p-6 lg:px-8" aria-label="Global">
          <div class="flex lg:flex-1 justify-center">
            <a href="/" class="-m-1.5 p-1.5">
              <span class="sr-only">Logo</span>
              <img class="h-60 w-auto" src="logo.png" alt="Logo">
            </a>
          </div>
          <div class="flex lg:hidden">
            <button type="button" class="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-400">
              <span class="sr-only">Open main menu</span>
              <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
              </svg>
            </button>
          </div>
        </nav>
        <!-- Mobile menu, show/hide based on menu open state. -->

      </header>

      <div class="relative isolate overflow-hidden pt-14">
        <img src="https://images.unsplash.com/photo-1521737604893-d14cc237f11d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2830&q=80&blend=111827&sat=-100&exp=15&blend-mode=multiply" alt="" class="absolute inset-0 -z-10 h-full w-full object-cover">
        <div class="absolute inset-x-0 -top-40 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80" aria-hidden="true">
          <div class="relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-tr from-[#ff80b5] to-[#9089fc] opacity-20 sm:left-[calc(50%-30rem)] sm:w-[72.1875rem]" style="clip-path: polygon(74.1% 44.1%, 100% 61.6%, 97.5% 26.9%, 85.5% 0.1%, 80.7% 2%, 72.5% 32.5%, 60.2% 62.4%, 52.4% 68.1%, 47.5% 58.3%, 45.2% 34.5%, 27.5% 76.7%, 0.1% 64.9%, 17.9% 100%, 27.6% 76.8%, 76.1% 97.7%, 74.1% 44.1%)"></div>
        </div>
        <div class="mx-auto max-w-2xl pb-60 pt-36 sm:pb-62 sm:pt-36">
          <div class="text-center">
            <div class="text-center mb-6">
              <h2 class="text-4xl font-bold tracking-tight text-white sm:text-6xl">Synex Outlet Store</h2>
            </div>
            <div class="shadow w-full rounded-lg">
              <div class="px-5 py-2 pt-4">
                <a href="products"
                   class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                  <span class="inline-block mr-2">Manage Product</span>
                </a>
              </div>

              <div class="px-5 py-2">
                <a href="stocks"
                   class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                  <span class="inline-block mr-2">Manage Stocks</span>
                </a>
              </div>

              <div class="px-5 py-2">
                <a href="takefromstock"
                   class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                  <span class="inline-block mr-2">Create Bill</span>
                </a>
              </div>

              <div class="px-5 py-2">
                <a href="inventoryreport"
                   class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                  <span class="inline-block mr-2">Manage Shelves</span>
                </a>
              </div>

              <div class="px-5 py-2">
                <a href="inventoryreport"
                   class="transition duration-200 bg-blue-500 hover:bg-blue-600 focus:bg-blue-700 focus:shadow-sm focus:ring-4 focus:ring-blue-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                  <span class="inline-block mr-2">Inventory Reports</span>
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
  </div>
</div>
</body>
</html>