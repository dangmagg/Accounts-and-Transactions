package com.example.rbcassignment.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.rbc.rbcaccountlibrary.AccountType;

import java.util.List;

public class BankAccountViewModel extends ViewModel {

    private final BankAccountProvider bankAccountProvider = new BankAccountProvider();

    public MutableLiveData<List<BankAccount>> selectedAccountsData;

    private MutableLiveData<List<BankAccount>> chequingData;
    private MutableLiveData<List<BankAccount>> creditData;
    private MutableLiveData<List<BankAccount>> loanData;
    private MutableLiveData<List<BankAccount>> mortgageData;

    /**
     * Provides the list of accounts for currently selected type to display on view
     */
    public LiveData<List<BankAccount>> getSelectedAccountsData() {
        if (this.selectedAccountsData == null) {
            this.selectedAccountsData = new MutableLiveData<>();
        }
        return this.selectedAccountsData;
    }

    public void setSelectedAccountsData(AccountType type) {
        switch (type) {
            case CHEQUING:
                this.selectedAccountsData.setValue(getChequingData().getValue());
                break;
            case CREDIT_CARD:
                this.selectedAccountsData.setValue(getCreditCardData().getValue());
                break;
            case LOAN:
                this.selectedAccountsData.setValue(getLoanData().getValue());
                break;
            case MORTGAGE:
                this.selectedAccountsData.setValue(getMortgageData().getValue());
                break;
        }
    }

    public LiveData<List<BankAccount>> getChequingData() {
        if (this.chequingData == null) {
            this.runAccountHandler(AccountType.CHEQUING);
            this.chequingData = new MutableLiveData<>();
        }
        return this.chequingData;
    }

    public LiveData<List<BankAccount>> getCreditCardData() {
        if (this.creditData == null) {
            this.runAccountHandler(AccountType.CREDIT_CARD);
            this.creditData = new MutableLiveData<>();
        }
        return this.creditData;
    }

    public LiveData<List<BankAccount>> getLoanData() {
        if (this.loanData == null) {
            this.runAccountHandler(AccountType.LOAN);
            this.loanData = new MutableLiveData<>();
        }
        return this.loanData;
    }

    public LiveData<List<BankAccount>> getMortgageData() {
        if (this.mortgageData == null) {
            this.runAccountHandler(AccountType.MORTGAGE);
            this.mortgageData = new MutableLiveData<>();
        }
        return this.mortgageData;
    }

    /**
     * Background service call to get the required list of bank account by type
     * @param type AccountType
     */
    private void runAccountHandler(AccountType type) {
        new Handler().post(() -> {
            List<BankAccount> list;
            switch (type) {
                case CHEQUING:
                    list = bankAccountProvider.getChequingAccountList();
                    chequingData.setValue(list);
                    break;
                case CREDIT_CARD:
                    list = bankAccountProvider.getCreditCardAccountList();
                    creditData.setValue(list);
                    break;
                case LOAN:
                    list = bankAccountProvider.getLoanAccountList();
                    loanData.setValue(list);
                    break;
                case MORTGAGE:
                    list = bankAccountProvider.getMortgageAccountList();
                    mortgageData.setValue(list);
                    break;
            }
            setSelectedAccountsData(type);
        });
    }



    /**
     * Formats balance to display on view
     * @return
     */
    public static String formatBalance(String balance) {
        return "$ " + balance;
    }

}
