package com.example.rbcassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public Account getAccountDetails() {
        return this.account;
    }

    public LiveData<List<Transaction>> getTransactionList() {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();
        if (this.transactionList == null) {
            // TODO: Fix being unable to get transaction error
            try {
                this.transactionList = AccountProvider.INSTANCE
                        .getTransactions(this.account.getNumber());
            } catch (Exception e) {
                this.transactionList = new ArrayList<>();
                data.setValue(this.transactionList);
                return data;
            }
//            this.transactionList = new ArrayList<>();
        }
        data.setValue(this.transactionList);
        return data;
    }
}
