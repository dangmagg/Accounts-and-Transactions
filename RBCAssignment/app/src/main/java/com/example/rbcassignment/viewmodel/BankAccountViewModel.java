package com.example.rbcassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.rbc.rbcaccountlibrary.Account;

import java.util.List;

public class BankAccountViewModel extends ViewModel {

    private BankAccountProvider bankAccountProvider = new BankAccountProvider();

    public MutableLiveData<List<Account>> mAccounts = new MutableLiveData<>();

    private LiveData<List<BankAccount>> chequingData;
    private LiveData<List<BankAccount>> creditData;
    private LiveData<List<BankAccount>> loanData;
    private LiveData<List<BankAccount>> mortgageData;

    public BankAccountViewModel() {
        this.chequingData = bankAccountProvider.getChequingAccountList();
        this.creditData = bankAccountProvider.getCreditCardAccountList();
        this.loanData = bankAccountProvider.getLoanAccountList();
        this.mortgageData = bankAccountProvider.getMortgageAccountList();
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
