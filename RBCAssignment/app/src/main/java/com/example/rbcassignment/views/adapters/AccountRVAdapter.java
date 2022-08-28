package com.example.rbcassignment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcassignment.R;
import com.example.rbcassignment.model.BankAccount;

import java.util.List;

public class AccountRVAdapter extends RecyclerView.Adapter<AccountRVAdapter.AccountViewHolder> {

    private List<BankAccount> accountList;

    public AccountRVAdapter(List<BankAccount> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.account_list_item, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        BankAccount account = this.accountList.get(position);
        // Set values to viewHolder
        holder.accountNameTv.setText(account.getAccount().getName());
        holder.accountNumTv.setText(account.getAccount().getNumber());
        holder.accountBalanceTv.setText(displayBalance(account.getAccount().getBalance()));
    }

    @Override
    public int getItemCount() {
        return this.accountList.size();
    }

    /**
     * Formats balance to display on view
     * @return
     */
    private String displayBalance(String balance) {
        return "$ " + balance;
    }

    /**
     * Used to initialize the views in the recycler view
     */
    class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView accountNameTv;
        private TextView accountNumTv;
        private TextView accountBalanceTv;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            this.accountNameTv = itemView.findViewById(R.id.account_name_tv);
            this.accountNumTv = itemView.findViewById(R.id.account_number_tv);
            this.accountBalanceTv = itemView.findViewById(R.id.account_balance_tv);
        }
    }
}
