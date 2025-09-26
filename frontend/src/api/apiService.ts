import axios from "axios";
import type { Account, Category, Transaction, RecurringTransaction } from "../types/models";

const api = axios.create({
    baseURL: "/api" // proxied to Spring Boot
});

// Accounts
export const getAccounts = () => api.get<Account[]>("/accounts");
export const createAccount = (account: Partial<Account>) => api.post<Account>("/accounts", account);

// Categories
export const getCategories = () => api.get<Category[]>("/categories");
export const createCategory = (category: Partial<Category>) => api.post<Category>("/categories", category);

// Transactions
export const getTransactions = () => api.get<Transaction[]>("/transactions");
export const createTransaction = (transaction: Partial<Transaction>) => api.post<Transaction>("/transactions", transaction);

// Recurring
export const getRecurring = () => api.get<RecurringTransaction[]>("/recurring");
export const createRecurring = (recurring: Partial<RecurringTransaction>) => api.post<RecurringTransaction>("/recurring", recurring);