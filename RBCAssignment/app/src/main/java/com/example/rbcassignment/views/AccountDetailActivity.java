package com.example.rbcassignment.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rbcassignment.R;
import com.example.rbcassignment.viewmodels.AccountDetailViewModel;
import com.example.rbcassignment.viewmodels.BankAccountViewModel;

public class AccountDetailActivity extends AppCompatActivity {

    AccountDetailViewModel accountDetailViewModel;

    private String name;
    private String number;
    private String balance;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detail_layout);

        this.accountDetailViewModel = new ViewModelProvider(this)
                .get(AccountDetailViewModel.class);

        Intent intent = getIntent();

        this.name = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NAME);
        this.number = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NUM);
        this.balance = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_BALANCE);
        this.type = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_TYPE);

        displayAccountDetails();

        // Transactions
        Log.i("TRANS", this.accountDetailViewModel.getTransaction(number, type).getValue().toString());

    }

    private void displayAccountDetails() {
        TextView nameTv = findViewById(R.id.account_detail_name_tv);
        TextView numberTv = findViewById(R.id.account_detail_num_tv);
        TextView balanceTv = findViewById(R.id.account_detail_bal_tv);
        nameTv.setText(this.name);
        numberTv.setText(this.number);
        balanceTv.setText(BankAccountViewModel.displayBalance(this.balance));
    }
}
