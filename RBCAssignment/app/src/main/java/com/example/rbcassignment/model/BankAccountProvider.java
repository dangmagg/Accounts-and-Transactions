package com.example.rbcassignment.model;

import android.util.Log;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.AccountType;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BankAccountProvider {

    private final List<Account> accountList = AccountProvider.INSTANCE.getAccountsList();

    private List<BankAccount> chequingAccList;
    private List<CreditCardBankAccount> creditCardAccList;
    private List<BankAccount> loanAccList;
    private List<BankAccount> mortgageAccList;

//    public BankAccountProvider() throws Exception {
//        initializeBankAccounts();
//    }

//    private void initializeBankAccounts() throws Exception {
//        Log.i("ACCOUNT LIST", this.accountList.toString());
//        for (Account account : this.accountList) {
//            Log.i("ACCOUNTS", account.toString());
//            switch (account.getType()) {
//                case CHEQUING:
//                    this.chequingAccList.add(createBankAccount(account));
//                    break;
//                case CREDIT_CARD:
//                    this.creditCardAccList.add(createCreditBankAccount(account));
//                    break;
//                case LOAN:
//                    this.loanAccList.add(createBankAccount(account));
//                    break;
//                case MORTGAGE:
//                    this.mortgageAccList.add(createBankAccount(account));
//                    break;
//            }
//        }
//    }

    /**
     * Finds and returns a List of accounts for only of AccountType that are not
     * CREDIT_CARD
     * @param type
     * @return
     */
    private List<BankAccount> getTypeOfAccountList(AccountType type) throws Exception {
        List<BankAccount> list = new ArrayList<>();
        for (Account account : this.accountList) {
//            Log.i("ACCOUNT", account.toString());
            if (account.getType() == type) {
                list.add(createBankAccount(account));
            }
        }
        return list;
    }

    /**
     * Given an Account, return a BankAccount that contains its transaction list data.
     * @param account
     * @return
     * @throws Exception
     */
//    private BankAccount createBankAccount(Account account) throws Exception {
//        BankAccount bankAccount;
//        // Find transaction list for given account
//        List<Transaction> transList = AccountProvider.INSTANCE
//                .getTransactions(account.getNumber());
//        // Credit Card accounts have an additional transaction list
//        if (isCreditCard(account)) {
//            List<Transaction> AdditionaltransList = AccountProvider.INSTANCE
//                    .getAdditionalCreditCardTransactions(account.getNumber());
//            bankAccount = new CreditCardBankAccount(account, transList, AdditionaltransList);
//        } else {
//            bankAccount = new BankAccount(account, transList);
//        }
//        return bankAccount;
//    }

    private BankAccount createBankAccount(Account account) throws Exception {
        return new BankAccount(account, getTransactionList(account));
    }

    // TODO: Concurrency?
    private CreditCardBankAccount createCreditBankAccount(Account account) throws Exception {
        return new CreditCardBankAccount(account, getTransactionList(account),
                getAdditionalTransactionList(account));
    }

    private List<Transaction> getTransactionList(Account account) throws Exception {
        return AccountProvider.INSTANCE.getTransactions(account.getNumber());
    }

    private List<Transaction> getAdditionalTransactionList(Account account) throws Exception {
        return AccountProvider.INSTANCE.getAdditionalCreditCardTransactions(account.getNumber());
    }

    private boolean isCreditCard(Account account) {
        return account.getType() == AccountType.CREDIT_CARD;
    }

    public List<BankAccount> getChequingAccountList() throws Exception {
        if (this.chequingAccList == null) {
            this.chequingAccList = this.getTypeOfAccountList(AccountType.CHEQUING);
        }
        return this.chequingAccList;
    }

    public List<CreditCardBankAccount> getCreditCardAccountList() throws Exception {
        if (this.creditCardAccList == null) {
            this.creditCardAccList = new ArrayList<>();
            for (Account account : this.accountList) {
                if (isCreditCard(account)) {
                    this.creditCardAccList.add(createCreditBankAccount(account));
                }
            }
        }
        return this.creditCardAccList;
    }

    public List<BankAccount> getLoanAccountList() throws Exception {
        if (this.loanAccList == null) {
            this.loanAccList = this.getTypeOfAccountList(AccountType.LOAN);
        }
        return this.loanAccList;
    }

    public List<BankAccount> getMortgageAccountList() throws Exception {
        if (this.mortgageAccList == null) {
            this.mortgageAccList = this.getTypeOfAccountList(AccountType.MORTGAGE);
        }
        return this.mortgageAccList;
    }

}
