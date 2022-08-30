package com.example.rbcassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.rbc.rbcaccountlibrary.AccountType;

import java.util.List;

public class BankAccountViewModel extends ViewModel {

    private BankAccountProvider bankAccountProvider = new BankAccountProvider();

    public MutableLiveData<List<BankAccount>> selectedAccountsData;

    private final LiveData<List<BankAccount>> chequingData;
    private final LiveData<List<BankAccount>> creditData;
    private final LiveData<List<BankAccount>> loanData;
    private final LiveData<List<BankAccount>> mortgageData;

    public BankAccountViewModel() {
        this.chequingData = bankAccountProvider.getChequingAccountList();
        this.creditData = bankAccountProvider.getCreditCardAccountList();
        this.loanData = bankAccountProvider.getLoanAccountList();
        this.mortgageData = bankAccountProvider.getMortgageAccountList();
    }

    /**
     * Provides the list of accounts for currently selected type
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
                this.selectedAccountsData.setValue(this.chequingData.getValue());
                break;
            case CREDIT_CARD:
                this.selectedAccountsData.setValue(this.creditData.getValue());
                break;
            case LOAN:
                this.selectedAccountsData.setValue(this.loanData.getValue());
                break;
            case MORTGAGE:
                this.selectedAccountsData.setValue(this.mortgageData.getValue());
                break;
        }
    }

    public LiveData<List<BankAccount>> getChequingData() {
        return this.chequingData;
    }

    public LiveData<List<BankAccount>> getCreditCardData() {
        return this.creditData;
    }

    public LiveData<List<BankAccount>> getLoanData() {
        return this.loanData;
    }

    public LiveData<List<BankAccount>> getMortgageData() {
        return this.mortgageData;
    }



    /**
     * Formats balance to display on view
     * @return
     */
    public static String formatBalance(String balance) {
        return "$ " + balance;
    }

}
