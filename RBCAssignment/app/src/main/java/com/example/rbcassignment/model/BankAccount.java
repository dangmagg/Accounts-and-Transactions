package com.example.rbcassignment.model;

import android.util.Log;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class BankAccount {

    private final Account account;
    private List<Transaction> transactionList;

    public BankAccount(Account account) {
        this.account = account;
    }

    public Account getAccountDetails() {
        return this.account;
    }

    public List<Transaction> getTransactionList() {
        if (this.transactionList == null) {
            try {
                this.transactionList = AccountProvider.INSTANCE
                        .getTransactions(this.account.getNumber());
            } catch (Exception e) {
                Log.e("TRANSACTION ERROR", e.getMessage());
                this.transactionList = getTransactionList();
            }
        }
        return this.transactionList;
    }
}
