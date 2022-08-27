package com.example.rbcassignment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.rbc.rbcaccountlibrary.Account;

import java.util.List;

public class BankAccountViewModel extends ViewModel {

    private BankAccountProvider bankAccountProvider = new BankAccountProvider();

    public MutableLiveData<List<Account>> mAccounts = new MutableLiveData<>();

    private LiveData<List<BankAccount>> accountData;

    public BankAccountViewModel() throws Exception {
        this.accountData = bankAccountProvider.getLiveChequingAccounts();
    }

    public LiveData<List<BankAccount>> getChequingData() {
//        if (mCheckingAccounts == null) {
//            mCheckingAccounts = bankAccountProvider.getChequingAccountList();
//        }
        return this.accountData;
    }
}
