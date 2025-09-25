import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080", // Spring boot backend
})

// Accounts
export const getAccounts = () => api.get("/accounts");
export const createAccount = (account) => api.post("/accounts", account);

// Categories
export const getCategories = () => api.get("/categories");
export const createCategory = (category) => api.post("/categories", category);

// Transactions
export const getTransaction = () => api.get("/transactions");
export const createTransaction = (transaction) => api.post("/transactions", transaction);

// Recurring Transactions
export const getRecurring = () => api.get("/recurring");
export const createRecurring = (recurring) => api.post("/recurring", recurring);

export default api;