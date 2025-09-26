import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TransactionsPage from "./pages/TransactionsPage";

// Router for pages like dashboard, accounts, transactions, budgets, etc.

export default function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path="/" element={<TransactionsPage />}/>
      </Routes>
    </Router>
  )
}
