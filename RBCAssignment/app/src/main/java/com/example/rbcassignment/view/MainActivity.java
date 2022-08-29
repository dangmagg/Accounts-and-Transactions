package com.example.rbcassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.rbcassignment.R;
import com.example.rbcassignment.databinding.ActivityMainBinding;
import com.example.rbcassignment.viewmodel.BankAccountViewModel;
import com.example.rbcassignment.view.adapter.AccountRVAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_NAME = "com.example.rbcassignment.ACCOUNT_NAME";
    public static final String EXTRA_ACCOUNT_NUM = "com.example.rbcassignment.ACCOUNT_NUM";
    public static final String EXTRA_ACCOUNT_BALANCE = "com.example.rbcassignment.ACCOUNT_BALANCE";
    public static final String EXTRA_ACCOUNT_TYPE = "com.example.rbcassignment.ACCOUNT_TYPE";

    BankAccountViewModel bankAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Binding ViewModel
        this.bankAccountViewModel = new ViewModelProvider(this)
                .get(BankAccountViewModel.class);
        binding.setBankAccountViewModel(this.bankAccountViewModel);
        binding.setLifecycleOwner(this);

        // Layout Manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

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


    }
}