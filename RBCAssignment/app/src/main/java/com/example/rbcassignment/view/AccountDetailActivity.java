package com.example.rbcassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcassignment.R;
import com.example.rbcassignment.databinding.AccountDetailLayoutBinding;
import com.example.rbcassignment.viewmodel.AccountDetailViewModel;
import com.example.rbcassignment.viewmodel.BankAccountViewModel;
import com.example.rbcassignment.view.adapter.TransactionRVAdapter;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;


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
        AccountDetailLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.account_detail_layout);

        this.layoutManager = new LinearLayoutManager(AccountDetailActivity.this);

        this.accountDetailViewModel = new ViewModelProvider(this)
                .get(AccountDetailViewModel.class);
        binding.setAccountDetailViewModel(this.accountDetailViewModel);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();

        this.name = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NAME);
        this.number = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_NUM);
        this.balance = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_BALANCE);
        this.type = intent.getStringExtra(MainActivity.EXTRA_ACCOUNT_TYPE);

        displayAdditionalTransView();
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
        RecyclerView transactionRv = findViewById(R.id.transaction_rv);
        this.accountDetailViewModel.executeTransThread(this.number, this.type);
        this.accountDetailViewModel.getTransaction().observe(this, list -> {
            // Display list of transactions
            setRvAdapter(list, transactionRv);
        });
        transactionRv.setLayoutManager(this.layoutManager);

        // Additional Transactions
        if (isCreditCard()) {
            LinearLayoutManager lm = new LinearLayoutManager(AccountDetailActivity.this);
            RecyclerView addTransRv = findViewById(R.id.additional_trans_rv);
            this.accountDetailViewModel.executeAdditionalTransThread(this.number, this.type);
            this.accountDetailViewModel.getAdditionalTransaction().observe(this, list -> {
                // Display additional transactions
                setRvAdapter(list, addTransRv);
            });
            addTransRv.setLayoutManager(lm);
        }
        this.accountDetailViewModel.executorShutDown();
    }

    /**
     * Show additional transaction views only for credit card accounts
     */
    private void displayAdditionalTransView() {
        if (isCreditCard()) {
            ConstraintLayout layout = findViewById(R.id.additional_trans_layout);
            layout.setVisibility(View.VISIBLE);
        } else {
            // Extend height of transaction recycler list view
            RecyclerView rv = findViewById(R.id.transaction_rv);
            rv.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
    }

    /**
     * Given a transaction list, display the contents to recycler view
     * @param list Transaction list
     * @param rv Recycler View
     */
    private void setRvAdapter(List<Transaction> list, RecyclerView rv) {
        if ((list != null) && !list.isEmpty()) {
            TransactionRVAdapter transAdapter = new TransactionRVAdapter(list);
            rv.setAdapter(transAdapter);
        }
    }

    private boolean isCreditCard() {
        return this.accountDetailViewModel.isCreditCard(this.type);
    }

}
