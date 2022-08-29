package com.example.rbcassignment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CreditCardBankAccount extends BankAccount {

    private List<Transaction> additionalTransactionList;

    public CreditCardBankAccount(Account account) {
        super(account);
    }

    public LiveData<List<Transaction>> getAdditionalTransactionList() {
        MutableLiveData<List<Transaction>> data = new MutableLiveData<>();
        if (this.additionalTransactionList == null) {
            // TODO: Fix being unable to get transaction error
            try {
                this.additionalTransactionList = AccountProvider.INSTANCE
                        .getAdditionalCreditCardTransactions(super.getAccountDetails().getNumber());
            } catch (Exception e) {
                this.additionalTransactionList = new ArrayList<>();
            }
        }
        data.setValue(this.additionalTransactionList);
        return data;
    }
}
