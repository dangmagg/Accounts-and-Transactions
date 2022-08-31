package com.example.rbcassignment.model;

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

    /**
     * Finds and returns a List of BankAccounts for only of AccountType
     * @param type
     * @return
     */
    private List<BankAccount> getTypeOfAccountList(AccountType type) {
        List<BankAccount> list = new ArrayList<>();
        for (Account account : this.accountList) {
            if (account.getType() == type) {
                list.add(new BankAccount(account));
            }
        }
        return list;
    }


    private boolean isCreditCard(Account account) {
        return account.getType() == AccountType.CREDIT_CARD;
    }

    public List<BankAccount> getChequingAccountList() {
        if (this.chequingAccList == null) {
            this.chequingAccList = this.getTypeOfAccountList(AccountType.CHEQUING);
        }
        return this.chequingAccList;
    }

    public List<BankAccount> getCreditCardAccountList() {
        if (this.creditCardAccList == null) {
            this.creditCardAccList = new ArrayList<>();
            for (Account account : this.accountList) {
                if (isCreditCard(account)) {
                    this.creditCardAccList.add(new CreditCardBankAccount(account));
                }
            }
        }
        return this.creditCardAccList;
    }

    public List<BankAccount> getLoanAccountList() {
        if (this.loanAccList == null) {
            this.loanAccList = this.getTypeOfAccountList(AccountType.LOAN);
        }
        return this.loanAccList;
    }

    public List<BankAccount> getMortgageAccountList() {
        if (this.mortgageAccList == null) {
            this.mortgageAccList = this.getTypeOfAccountList(AccountType.MORTGAGE);
        }
        return this.mortgageAccList;
    }

}
