package com.example.rbcassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
            // TODO: Show account details
            Toast.makeText(getApplicationContext(), account.getAccount().getNumber(), Toast.LENGTH_LONG).show();
        });

        // Setting layout manager and items for recycler view list
        chequingRv.setAdapter(chequingRVAdapter);

        chequingRv.setLayoutManager(layoutManager);

        // TODO: TESTING
        Log.i("LIVEDATA CHEQ", this.bankAccountViewModel.getChequingData().getValue().get(0).getAccount().getBalance());
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