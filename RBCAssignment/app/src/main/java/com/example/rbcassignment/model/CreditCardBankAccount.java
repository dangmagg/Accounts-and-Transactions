package com.example.rbcassignment.model;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class CreditCardBankAccount extends BankAccount {

    private List<Transaction> additionalTransactionList;

    public CreditCardBankAccount(Account account, List<Transaction> transactionList,
                                 List<Transaction> additionalTransactionList) {
        super(account, transactionList);
        this.additionalTransactionList = additionalTransactionList;
    }

    public List<Transaction> getAdditionalTransactionList() {
        return this.additionalTransactionList;
    }

}
