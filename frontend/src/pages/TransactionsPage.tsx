import { useEffect, useState } from "react";
import { getTransactions, createTransaction, getAccounts, getCategories } from "../api/apiService";
import type { Transaction, Account, Category } from "../types/models";

// Page showing a list of transactions and to add new transactions

export default function TransactionPage() {

    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [categories, setCategories] = useState<Category[]>([]);
    const [amount, setAmount] = useState("");
    const [selectedAccountId, setSelectedAccountId] = useState<number | "">("");
    const [selectedCategoryId, setSelectedCategoryId] =  useState<number | "">("");

    // get existing transactions
    // useEffect(() => {
    //     getTransactions()
    //         .then(res => setTransactions(res.data))
    //         .catch(err => console.error(err));
    // }, []);

    useEffect(() => {
        getAccounts().then(res => setAccounts(res.data));
        getCategories().then(res => setCategories(res.data));
        getTransactions().then(res => setTransactions(res.data));
    }, []);

    // creating a new transaction
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if(!selectedAccountId || !selectedCategoryId) return;

        const newTransaction: Partial<Transaction> = {
            amount: parseFloat(amount),
            date: new Date().toISOString().split("T")[0], // today's date
            account: { id: selectedAccountId } as Account,
            category: { id: selectedCategoryId } as Category,
        };

        try {
            const res = await createTransaction(newTransaction);
            setTransactions([...transactions, res.data]);

            setAmount("");
            setSelectedAccountId("");
            setSelectedCategoryId("");
        } catch (err) {
            console.error("Error creating transaction", err);
        }
    };

    return (
        <div className="p-6">
            <h1 className="text-2xl font-bold mb-4">Transactions</h1>

            {/* Transaction form */}
            <form onSubmit={handleSubmit} className="mb-6 space-x-2">
                <input 
                    type="number" 
                    placeholder="Amount"
                    value={amount}
                    onChange={e => setAmount(e.target.value)}
                    className="border p-2 rounded"
                    required
                />
                
                {/* Account dropdown */}
                <select 
                    value={selectedAccountId}
                    onChange={e => setSelectedAccountId(Number(e.target.value))}
                    className="border p-2 rounded"
                    required
                >
                    <option value="">Select Account</option>
                    {accounts.map(account => (
                        <option key={account.id} value={account.id}>
                            {account.type} (Balance: ${account.balance})
                        </option>
                    ))}
                </select>

                {/* Category dropdown */}
                <select
                    value={selectedCategoryId}
                    onChange={e => setSelectedCategoryId(Number(e.target.value))}
                    className="border p-2 rounded"
                    required
                >
                    <option value="">Select Category</option>
                    {categories.map(category => (
                        <option key={category.id} value={category.id}>
                            {category.name} ({category.type})
                        </option>
                    ))}
                </select>

                <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">Add</button>
            </form>

            {/* Transactions List */}
            <ul style={{ listStyle: "none", padding: 0}} className="space-y-2">
                {transactions.map(transaction => (
                    <li key={transaction.id} className="border p-3 rounded">
                        <p>Amount: {transaction.amount}</p>
                        <p>Date: {transaction.date}</p>
                        <p>Account: {transaction.account.id}</p>
                        <p>Category: {transaction.category.id}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}