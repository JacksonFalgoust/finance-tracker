// Shared TypeScript interface with backend
// Defines types so that the frontend matches the backend

export interface Account {
    id: number;
    name: string;
    type: "CHECKING" | "SAVINGS" | "CREDIT_CARD" | "CASH" | "INVESTMENT";
    balance:number;
}

export interface Category {
    id: number;
    name: string;
    type: "EXPENSE" | "INCOME";
    spendThisMonth?: number;
}

export interface Transaction {
    id?: number;
    amount: number;
    date?: string;
    account: Account;
    category: Category;
    isRecurring?: boolean;
}

export interface RecurringTransaction extends Transaction {
    startDate?: string;
    frequency: "DAILY" | "WEEKLY" | "BIWEEKLY" | "MONTHLY" | "YEARLY";
    nextOccurrence?: string;
}