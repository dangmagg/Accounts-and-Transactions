package com.example.rbcassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcassignment.R;
import com.example.rbcassignment.viewmodel.AccountDetailViewModel;
import com.example.rbcassignment.viewmodel.BankAccountViewModel;
import com.example.rbcassignment.view.adapter.TransactionRVAdapter;

public class AccountDetailActivity extends AppCompatActivity {

    LinearLayoutManager layoutManager;
    AccountDetailViewModel accountDetailViewModel;

    private String name;
    private String number;
    private String balance;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detail_layout);

        this.layoutManager = new LinearLayoutManager(AccountDetailActivity.this);

        this.accountDetailViewModel = new ViewModelProvider(this)
                .get(AccountDetailViewModel.class);

        Intent intent = getIntent();

        this.name = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NAME);
        this.number = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NUM);
        this.balance = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_BALANCE);
        this.type = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_TYPE);

        displayAccountDetails();
        displayTransactions();

    }

    private void displayAccountDetails() {
        TextView nameTv = findViewById(R.id.account_detail_name_tv);
        TextView numberTv = findViewById(R.id.account_detail_num_tv);
        TextView balanceTv = findViewById(R.id.account_detail_bal_tv);
        nameTv.setText(this.name);
        numberTv.setText(this.number);
        balanceTv.setText(BankAccountViewModel.formatBalance(this.balance));
    }

    private void displayTransactions() {
//        Log.i("TRANS", this.accountDetailViewModel.getTransaction(number, type).getValue().toString());
        RecyclerView transactionRv = findViewById(R.id.transaction_rv);
        TransactionRVAdapter transAdapter = new TransactionRVAdapter(this.accountDetailViewModel
                                                                         .getTransaction(this.number, this.type)
                                                                         .getValue());
        transactionRv.setAdapter(transAdapter);
        transactionRv.setLayoutManager(this.layoutManager);
    }
}
