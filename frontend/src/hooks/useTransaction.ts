import { useState, useEffect } from "react";
import { getTransactions } from "../api/apiService";
import type { Transaction } from "../types/models";

// Custom hook to fetch and manage transactions

export function useTransactions() {

    const [transactions, setTransactions] = useState<Transaction[]>([]);

    useEffect(() => {
        getTransactions().then(res => setTransactions(res.data));
    }, []);

    return transactions;
}