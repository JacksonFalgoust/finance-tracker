import { useEffect, useState } from "react";
import { getTransactions, createTransaction, getAccounts, getCategories, createRecurring, getRecurring } from "../api/apiService";
import type { Transaction, Account, Category, RecurringTransaction } from "../types/models";

// Page showing a list of transactions and to add new transactions

export default function TransactionPage() {

    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [scheduledTransactions, setScheduledTransactions] = useState<RecurringTransaction[]>([]);
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [categories, setCategories] = useState<Category[]>([]);
    const [amount, setAmount] = useState("");
    const [selectedAccountId, setSelectedAccountId] = useState<number | "">("");
    const [selectedCategoryId, setSelectedCategoryId] =  useState<number | "">("");
    const [isRecurring, setIsRecurring] = useState(false);
    type Frequency = "DAILY" | "WEEKLY" | "BIWEEKLY" | "MONTHLY" | "YEARLY";
    const [frequency, setFrequency] = useState<Frequency>("MONTHLY");

    // get existing transactions, accounts, and categories
    useEffect(() => {
        getAccounts().then(res => setAccounts(res.data));
        getCategories().then(res => setCategories(res.data));
        getTransactions().then(res => setTransactions(res.data));
        getRecurring().then(res => setScheduledTransactions(res.data));
    }, []);

    // creating a new transaction
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if(!selectedAccountId || !selectedCategoryId) return;

        if (isRecurring) {
            const recurringTransaction: RecurringTransaction = {
                amount: parseFloat(amount),
                date: new Date().toISOString().split("T")[0], // today's date
                account: { id: selectedAccountId } as Account,
                category: { id: selectedCategoryId } as Category,
                isRecurring: true,
                frequency,
            };
            await createRecurring(recurringTransaction);
        } else {
            const transaction: Partial<Transaction> = {
                amount: parseFloat(amount),
                date: new Date().toISOString().split("T")[0], // today's date
                account: { id: selectedAccountId } as Account,
                category: { id: selectedCategoryId } as Category,
                isRecurring: false,
            };
            await createTransaction(transaction)
        }

        const transactions = await getTransactions();
        setTransactions(transactions.data);

        setAmount("");
        setSelectedAccountId("");
        setSelectedCategoryId("");
        setIsRecurring(false);
        setFrequency("MONTHLY");

    };

    return (
        <div className="min-h-screen flex items-center justify-start flex-col">
            <div className="w-full max-w-6xl p-6 text-center">
                <h1 className="text-2xl font-bold mb-4 text-center">Transactions</h1>

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

                    {/* Recurring toggle */}
                    <label className="items-center space-x-2">
                        <input type="checkbox" checked={isRecurring} onChange={e => setIsRecurring(e.target.checked)} />
                        <span>Recurring</span>
                    </label>

                    {/* Frequency dropdown only if recurring */}
                    {isRecurring && (
                        <select
                            value={frequency}
                            onChange={(e) => setFrequency(e.target.value as Frequency)}
                            className="border p-2 rounded"
                        >
                            {["DAILY", "WEEKLY", "BIWEEKLY", "MONTHLY", "YEARLY"].map((frequency) => (
                                <option key={frequency} value={frequency}>
                                    {frequency}
                                </option>
                            ))}
                        </select>
                    )}

                    <button type="submit" className="bg-blue-500 px-4 py-2 rounded">Add</button>
                </form>
            </div>
            
            <div className="flex gap-8 flex-row justify-center w-full">
                <div className="flex-1 max-w-md">
                    {/* Transactions List */}
                    <h2 className="text-xl font-bold mb-2 text-center">Transactions</h2>
                    <ul className="list-none p-0 space-y-3">
                        {transactions.map(transaction => (
                            <li key={transaction.id} className="p-3 bg-white shadow-mb rounded-lg border hover:shadow-lg transition text-center">
                                <p className="font-semibold text-center">Amount: ${transaction.amount}</p>
                                <p className="text-sm text-gray-600 text-center">Account: {transaction.account.type} | Category: {transaction.category.type}</p>
                                <p className="text-xs text-gray-400 text-center">Date: {transaction.date}</p>
                            </li>
                        ))}
                    </ul>
                </div>

                <div className="flex-1 max-w-md">
                    {/* Recurring Transactions List */}
                    <h2 className="text-xl font-bold mb-2 text-center">Scheduled Transactions</h2>
                    <ul className="list-none p-0 space-y-3">
                        {scheduledTransactions.map(transaction => (
                            <li key={transaction.id} className="p-3 bg-white shadow-md rounded-lg border hover:shadow-lg transition text-center">
                                <p className="font-semibold text-center">Amount: ${transaction.amount}</p>
                                <p className="text-sm text-gray-600 text-center">Account: {transaction.account.type} | Category: {transaction.category.type}</p>
                                <p className="text-xs text-gray-400 text-center">Next Occurrence: {transaction.nextOccurrence} | Frequency: {transaction.frequency}</p>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>    
    );
}