package com.example.rbcassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.rbcassignment.R;
import com.example.rbcassignment.viewmodels.BankAccountViewModel;
import com.example.rbcassignment.views.adapters.AccountRVAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_NAME = "com.example.rbcassignment.ACCOUNT_NAME";
    public static final String EXTRA_ACCOUNT_NUM = "com.example.rbcassignment.ACCOUNT_NUM";
    public static final String EXTRA_ACCOUNT_BALANCE = "com.example.rbcassignment.ACCOUNT_BALANCE";
    public static final String EXTRA_ACCOUNT_TYPE = "com.example.rbcassignment.ACCOUNT_TYPE";

    BankAccountViewModel bankAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        // Livedata
        this.bankAccountViewModel = new ViewModelProvider(this)
                .get(BankAccountViewModel.class);

        // Initialize Recycler Views
        RecyclerView chequingRv = findViewById(R.id.chequing_rv);

        // Passing values to adapter
        AccountRVAdapter chequingRVAdapter = new AccountRVAdapter(this.bankAccountViewModel
                                                                    .getChequingData()
                                                                    .getValue());
        chequingRVAdapter.setOnClickListener(account -> {
            // Start new activity to display account details and transactions
            Intent intent = new Intent(this, AccountDetailActivity.class);
            intent.putExtra(EXTRA_ACCOUNT_NAME, account.getAccountDetails().getName());
            intent.putExtra(EXTRA_ACCOUNT_NUM, account.getAccountDetails().getNumber());
            intent.putExtra(EXTRA_ACCOUNT_BALANCE, account.getAccountDetails().getBalance());
            intent.putExtra(EXTRA_ACCOUNT_TYPE, account.getAccountDetails().getType().toString());
            startActivity(intent);
        });

        // Setting layout manager and items for recycler view list
        chequingRv.setAdapter(chequingRVAdapter);

        chequingRv.setLayoutManager(layoutManager);

        // TODO: TESTING
        Log.i("LIVEDATA CHEQ", this.bankAccountViewModel.getChequingData().getValue().get(0).getAccountDetails().getBalance());
//        BankAccountProvider a = new BankAccountProvider();
//        List<BankAccount> c = null;
//        try {
//            c = a.getChequingAccountList();
//            Log.i("CHEQUING", c.get(0).getAccount().getNumber());
////            Log.i("CHEQUING TRANS", c.get(0).getTransactionList().toString());
////            List<CreditCardBankAccount> cc = a.getCreditCardAccountList();
////            Log.i("CC", cc.toString());
////            Log.i("CC TRANS", cc.get(0).getAdditionalTransactionList().toString());
////            List<Transaction> trans = AccountProvider.INSTANCE.getTransactions(c.get(0).getAccount().getNumber());
////            Log.i("Trans", trans.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}