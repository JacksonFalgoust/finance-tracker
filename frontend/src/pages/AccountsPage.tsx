import { useEffect, useState } from "react";
import {createAccount, getAccounts} from "../api/apiService";
import type {Account} from "../types/models";

export default function AccountsPage() {

  const [accounts, setAccounts] = useState<Account[]>([]);
  const [balance, setBalance] = useState("");
  const [name, setName] = useState("");
  type Type = "CHECKING" | "SAVINGS" | "CREDIT_CARD" | "CASH" | "INVESTMENT";
  const [ type, setType] = useState<Type>("CHECKING");

  useEffect(() => {
    getAccounts().then(res => setAccounts(res.data));
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if(!type) return;

    try {
      const account: Partial<Account> = {
        balance: parseFloat(balance),
        name,
        type,
      };

      await createAccount(account);
    } catch (error) {
      console.error("Could not create account");
    }

    const accounts = await getAccounts();

    setAccounts(accounts.data);

    setName("");
    setBalance("");
    setType("CHECKING");


  }

  return (
    <div className="min-h-screen flex items-center justify-start flex-col">
      <div className="w-full max-w-6xl p-6 text-center">
        <h1 className="text-2xl font-bold mb-4 text-center">Accounts</h1>

        {/* Account form */}
        <form onSubmit={handleSubmit} className="mb-6 space-x-2">
          <input 
            type="number"
            placeholder="Balance"
            value={balance}
            onChange={e => setBalance(e.target.value)}
            className="border p-2 rounded"
            required
          />

          {/* Type dropdown */}
          <select
            value={type}
            onChange={e => setType(e.target.value as Type)}
            className="border p-2 rounded"
          > 
            {["CHECKING", "SAVING", "CREDIT_CARD", "CASH", "INVESTMENT"].map((type) => (
              <option key={type} value={type}>
                {type}
              </option>
            ))}
            </select>
        </form>
      </div>
    </div>
  );
}