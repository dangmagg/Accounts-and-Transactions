package com.example.rbcassignment.model;

import android.util.Log;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class CreditCardBankAccount extends BankAccount {

    private List<Transaction> additionalTransactionList;

    public CreditCardBankAccount(Account account) {
        super(account);
    }

    public List<Transaction> getAdditionalTransactionList() {
        if (this.additionalTransactionList == null) {
            try {
                this.additionalTransactionList = AccountProvider.INSTANCE
                        .getAdditionalCreditCardTransactions(super.getAccountDetails().getNumber());
            } catch (Exception e) {
                Log.e("TRANSACTION ERROR", e.getMessage());
                this.additionalTransactionList = getAdditionalTransactionList();
            }
        }
        return this.additionalTransactionList;
    }
}
