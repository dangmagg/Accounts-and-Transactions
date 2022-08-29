package com.example.rbcassignment.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.AccountType;

import java.util.ArrayList;
import java.util.List;

public class BankAccountProvider {

    private final List<Account> accountList = AccountProvider.INSTANCE.getAccountsList();

    private List<BankAccount> chequingAccList;
    private List<BankAccount> creditCardAccList;
    private List<BankAccount> loanAccList;
    private List<BankAccount> mortgageAccList;

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
     * Finds and returns a List of BankAccounts for only of AccountType
     * @param type
     * @return
     */
    private List<BankAccount> getTypeOfAccountList(AccountType type) {
        List<BankAccount> list = new ArrayList<>();
        for (Account account : this.accountList) {
//            Log.i("ACCOUNT", account.toString());
            if (account.getType() == type) {
                list.add(new BankAccount(account));
            }
        }
        return list;
    }


    private boolean isCreditCard(Account account) {
        return account.getType() == AccountType.CREDIT_CARD;
    }

//    public List<BankAccount> getChequingAccountList() throws Exception {
//        if (this.chequingAccList == null) {
//            this.chequingAccList = this.getTypeOfAccountList(AccountType.CHEQUING);
//        }
//        return this.chequingAccList;
//    }

    public LiveData<List<BankAccount>> getChequingAccountList() {
        MutableLiveData<List<BankAccount>> data = new MutableLiveData<>();
        if (this.chequingAccList == null) {
            this.chequingAccList = this.getTypeOfAccountList(AccountType.CHEQUING);
        }
        data.setValue(this.chequingAccList);
        return data;
    }

//    public List<CreditCardBankAccount> getCreditCardAccountList() throws Exception {
//        if (this.creditCardAccList == null) {
//            this.creditCardAccList = new ArrayList<>();
//            for (Account account : this.accountList) {
//                if (isCreditCard(account)) {
//                    this.creditCardAccList.add(new CreditCardBankAccount(account));
//                }
//            }
//        }
//        return this.creditCardAccList;
//    }

    // TODO: Delete later?
//    public LiveData<List<CreditCardBankAccount>> getCreditCardAccountList() {
//        MutableLiveData<List<CreditCardBankAccount>> data = new MutableLiveData<>();
//        if (this.creditCardAccList == null) {
//            this.creditCardAccList = new ArrayList<>();
//            for (Account account : this.accountList) {
//                if (isCreditCard(account)) {
//                    this.creditCardAccList.add(new CreditCardBankAccount(account));
//                }
//            }
//        }
//        data.setValue(this.creditCardAccList);
//        return data;
//    }

    public LiveData<List<BankAccount>> getCreditCardAccountList() {
        MutableLiveData<List<BankAccount>> data = new MutableLiveData<>();
        if (this.creditCardAccList == null) {
            this.creditCardAccList = this.getTypeOfAccountList(AccountType.CREDIT_CARD);
        }
        data.setValue(this.creditCardAccList);
        return data;
    }

//    public List<BankAccount> getLoanAccountList() throws Exception {
//        if (this.loanAccList == null) {
//            this.loanAccList = this.getTypeOfAccountList(AccountType.LOAN);
//        }
//        return this.loanAccList;
//    }

    public LiveData<List<BankAccount>> getLoanAccountList() {
        MutableLiveData<List<BankAccount>> data = new MutableLiveData<>();
        if (this.loanAccList == null) {
            this.loanAccList = this.getTypeOfAccountList(AccountType.LOAN);
        }
        data.setValue(this.loanAccList);
        return data;
    }

//    public List<BankAccount> getMortgageAccountList() throws Exception {
//        if (this.mortgageAccList == null) {
//            this.mortgageAccList = this.getTypeOfAccountList(AccountType.MORTGAGE);
//        }
//        return this.mortgageAccList;
//    }

    public LiveData<List<BankAccount>> getMortgageAccountList() {
        MutableLiveData<List<BankAccount>> data = new MutableLiveData<>();
        if (this.mortgageAccList == null) {
            this.mortgageAccList = this.getTypeOfAccountList(AccountType.MORTGAGE);
        }
        data.setValue(this.mortgageAccList);
        return data;
    }

}
