package com.example.rbcassignment.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rbcassignment.R;
import com.example.rbcassignment.viewmodels.BankAccountViewModel;

public class AccountDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_detail_layout);

        Intent intent = getIntent();

        String name = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NAME);
        String number = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NUM);
        String balance = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_BALANCE);

        // Display Account details
        TextView nameTv = findViewById(R.id.account_detail_name_tv);
        TextView numberTv = findViewById(R.id.account_detail_num_tv);
        TextView balanceTv = findViewById(R.id.account_detail_bal_tv);
        nameTv.setText(name);
        numberTv.setText(number);
        balanceTv.setText(BankAccountViewModel.displayBalance(balance));

    }
}
