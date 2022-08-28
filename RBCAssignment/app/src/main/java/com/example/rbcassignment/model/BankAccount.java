package com.example.rbcassignment.model;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private Account account;
    private List<Transaction> transactionList;

    public BankAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public List<Transaction> getTransactionList() {
        if (this.transactionList == null) {
            // TODO: Fix being unable to get transaction error
//            try {
//                this.transactionList = AccountProvider.INSTANCE.getTransactions(this.account.getNumber());
//            } catch (Exception e) {
//                this.transactionList = new ArrayList<>();
//                return this.transactionList;
//            }
            this.transactionList = new ArrayList<>();
        }
        return this.transactionList;
    }
}
