package com.example.rbcassignment.model;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class BankAccount {

    private Account account;
    private List<Transaction> transactionList;

    public BankAccount(Account account, List<Transaction> transactionList) {
        this.account = account;
        this.transactionList = transactionList;
    }

    public Account getAccount() {
        return this.account;
    }

    public List<Transaction> getTransactionList() {
        return this.transactionList;
    }
}
