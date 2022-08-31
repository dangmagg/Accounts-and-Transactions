package com.example.rbcassignment.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.example.rbcassignment.model.CreditCardBankAccount;
import com.rbc.rbcaccountlibrary.AccountType;
import com.rbc.rbcaccountlibrary.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountDetailViewModel extends ViewModel {

    private final BankAccountProvider bankAccountProvider = new BankAccountProvider();

    // 2 Threads to run transactions in parallel
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    private MutableLiveData<List<Transaction>> transListData;
    private MutableLiveData<List<Transaction>> additionalTransListData;

    public LiveData<List<Transaction>> getTransaction() {
        if (this.transListData == null) {
            this.transListData = new MutableLiveData<>();
        }
        return this.transListData;
    }

    /**
     * Additional transactions are only available to Credit Card type accounts
     */
    public LiveData<List<Transaction>> getAdditionalTransaction() {
        if (this.additionalTransListData == null) {
            this.additionalTransListData = new MutableLiveData<>();
        }
        return this.additionalTransListData;
    }

    /**
     * Start a background thread to fetch transaction data
     */
    public void executeTransThread(String accountNum, String type) {
        this.executor.execute(() -> {
            BankAccount account = getBankAccount(accountNum, type);
            if (account != null) {
                List<Transaction> list = account.getTransactionList();
                transListData.postValue(list);
            }
        });
    }

    public void executeAdditionalTransThread(String accountNum, String type) {
        this.executor.execute(() -> {
            BankAccount account = getBankAccount(accountNum, type);
            if ((account != null) && isCreditCard(type)) {
                CreditCardBankAccount creditAccount = new CreditCardBankAccount(account.getAccountDetails());
                List<Transaction> list = creditAccount.getAdditionalTransactionList();
                additionalTransListData.postValue(list);
            }
        });
    }

    public void executorShutDown() {
        this.executor.shutdown();
    }

    public boolean isCreditCard(String type) {
        return AccountType.valueOf(type) == AccountType.CREDIT_CARD;
    }

    /**
     * Find and return BankAccount of the corresponding account number
     */
    private BankAccount getBankAccount(String accountNum, String type) {
        List<BankAccount> accountList;
        switch (AccountType.valueOf(type)) {
            case CHEQUING:
                accountList = bankAccountProvider.getChequingAccountList();
                break;
            case CREDIT_CARD:
                accountList = bankAccountProvider.getCreditCardAccountList();
                break;
            case LOAN:
                accountList = bankAccountProvider.getLoanAccountList();
                break;
            case MORTGAGE:
                accountList = bankAccountProvider.getMortgageAccountList();
                break;
            default:
                return null;
        }
        // Search
        for (BankAccount account : Objects.requireNonNull(accountList)) {
            if (account.getAccountDetails().getNumber().equals(accountNum)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Formats amount to display on view
     */
    public static String formatAmount(String amount) {
        return BankAccountViewModel.formatBalance(amount);
    }

    /**
     * Formats date to display on transaction list item
     * @param calendar date of transaction
     * @return String
     */
    public static String formatDate(Calendar calendar) {
        @SuppressLint("SimpleDateFormat")
        DateFormat formatter = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");
        return formatter.format(calendar.getTime());
    }
}
