package com.example.rbcassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.rbcassignment.R;
import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.example.rbcassignment.model.CreditCardBankAccount;
import com.example.rbcassignment.viewmodels.BankAccountViewModel;
import com.example.rbcassignment.views.adapters.AccountRVAdapter;
import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        // Livedata
        BankAccountViewModel bankAccountViewModel = new ViewModelProvider(this)
                .get(BankAccountViewModel.class);

        // Initialize Recycler Views
        RecyclerView chequingRv = findViewById(R.id.chequing_rv);

        // Passing values to adapter
        AccountRVAdapter accountRVAdapter = new AccountRVAdapter(bankAccountViewModel.getChequingData().getValue());

        // Setting layout manager and items for recycler view list
        chequingRv.setAdapter(accountRVAdapter);

        chequingRv.setLayoutManager(layoutManager);

        // TODO: TESTING
        Log.i("LIVEDATA CHEQ", bankAccountViewModel.getChequingData().getValue().get(0).getAccount().getBalance());
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