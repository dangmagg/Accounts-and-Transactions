package com.example.rbcassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rbcassignment.R;
import com.example.rbcassignment.databinding.ActivityMainBinding;
import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.viewmodel.BankAccountViewModel;
import com.example.rbcassignment.view.adapter.AccountRVAdapter;
import com.rbc.rbcaccountlibrary.AccountType;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ACCOUNT_NAME = "com.example.rbcassignment.ACCOUNT_NAME";
    public static final String EXTRA_ACCOUNT_NUM = "com.example.rbcassignment.ACCOUNT_NUM";
    public static final String EXTRA_ACCOUNT_BALANCE = "com.example.rbcassignment.ACCOUNT_BALANCE";
    public static final String EXTRA_ACCOUNT_TYPE = "com.example.rbcassignment.ACCOUNT_TYPE";

    BankAccountViewModel bankAccountViewModel;
    RecyclerView accountsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Binding ViewModel
        this.bankAccountViewModel = new ViewModelProvider(this)
                .get(BankAccountViewModel.class);
        binding.setBankAccountViewModel(this.bankAccountViewModel);
        binding.setLifecycleOwner(this);

        initSpinner();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        // Initialize Recycler View
        this.accountsRV = findViewById(R.id.accounts_rv);
        AccountRVAdapter accountRvAdapter = new AccountRVAdapter(this.bankAccountViewModel
                                                                    .getSelectedAccountsData()
                                                                    .getValue());
        accountRvAdapter.setOnClickListener(this::launchAccountDetailActivity);

        this.accountsRV.setAdapter(accountRvAdapter);
        this.accountsRV.setLayoutManager(layoutManager);

        this.bankAccountViewModel.getSelectedAccountsData().observe(this, list -> {
            // Repopulate recycler list view
            AccountRVAdapter adapter = new AccountRVAdapter(list);
            adapter.setOnClickListener(this::launchAccountDetailActivity);
            this.accountsRV.setAdapter(adapter);
        });

    }

    /**
     * Initialize account type selector
     */
    private void initSpinner() {
        Spinner spinner = findViewById(R.id.account_type_spinner);
        // Account type strings from resource
        String[] spinnerPaths = {getString(R.string.chequing), getString(R.string.credit_card),
                getString(R.string.loan), getString(R.string.mortgage)};

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, spinnerPaths);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Start new activity to display account details and transactions
     */
    private void launchAccountDetailActivity(BankAccount account) {
        Intent intent = new Intent(this, AccountDetailActivity.class);
        intent.putExtra(EXTRA_ACCOUNT_NAME, account.getAccountDetails().getName());
        intent.putExtra(EXTRA_ACCOUNT_NUM, account.getAccountDetails().getNumber());
        intent.putExtra(EXTRA_ACCOUNT_BALANCE, account.getAccountDetails().getBalance());
        intent.putExtra(EXTRA_ACCOUNT_TYPE, account.getAccountDetails().getType().toString());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                // CHEQUING
                this.bankAccountViewModel.setSelectedAccountsData(AccountType.CHEQUING);
                break;
            case 1:
                // CREDIT CARD
                this.bankAccountViewModel.setSelectedAccountsData(AccountType.CREDIT_CARD);
                break;
            case 2:
                // LOAN
                this.bankAccountViewModel.setSelectedAccountsData(AccountType.LOAN);
                break;
            case 3:
                // MORTGAGE
                this.bankAccountViewModel.setSelectedAccountsData(AccountType.MORTGAGE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}