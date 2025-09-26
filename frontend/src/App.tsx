import React, { useState } from 'react'
import './App.css'
import TransactionsPage from "./pages/TransactionsPage";
import CategoriesPage from './pages/CategoriesPage';
import AccountsPage from './pages/AccountsPage';

// Router for pages like dashboard, accounts, transactions, budgets, etc.

export default function App() {
  const [page, setPage] = useState("welcome");

  return (
    <div className='min-h-screen bg-gray-100'>
      {/* Sticky navbar */}
      <nav className='bg-gray-900 text-white px-6 py-4 flex justify-between items-center shadow-md sticky top-0 z-50'>
        <h1 className='text-xl font-bold'>Personal Finance Tracker</h1>

        <div className='space-x-6'>
          <button
            onClick={() => setPage("welcome")}
            className='hover:text-blue-400 transition'>
              Home
            </button>

          <button
            onClick={() => setPage("transactions")}
            className='hover:text-blue-400 transition'>
              Transactions
            </button>

            <button
            onClick={() => setPage("categories")}
            className='hover:text-blue-400 transition'>
              Categories
            </button>

            <button
            onClick={() => setPage("accounts")}
            className='hover:text-blue-400 transition'>
              Accounts
            </button>
        </div>
      </nav>

      {/* Page Content */}
      <div className='p-6' text-center>
        {page === "welcome" && (
          <div>
            <h2 className='text-3xl font-bold mb-4'>Welcome to your Personal Finance Tracker</h2>
            <p className='text-lg text-gray-700'>Use the navigation above to manage your transactions, categories, and accounts</p>
          </div>
        )}
        {page === "transactions" && <TransactionsPage/>}
        {page === "categories" && <CategoriesPage/>}
        {page === "accounts" && <AccountsPage />}
      </div>
    </div>
  )
}
