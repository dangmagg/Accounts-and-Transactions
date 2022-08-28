package com.example.rbcassignment.model;

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

    public List<Transaction> getAdditionalTransactionList() {
        if (this.additionalTransactionList == null) {
            // TODO: Fix being unable to get transaction error
//            try {
//                this.additionalTransactionList = AccountProvider.INSTANCE.getAdditionalCreditCardTransactions(super.getAccount().getNumber());
//            } catch (Exception e) {
//                this.additionalTransactionList = new ArrayList<>();
//                return this.additionalTransactionList;
//            }
            this.additionalTransactionList = new ArrayList<>();
        }
        return this.additionalTransactionList;
    }
}
