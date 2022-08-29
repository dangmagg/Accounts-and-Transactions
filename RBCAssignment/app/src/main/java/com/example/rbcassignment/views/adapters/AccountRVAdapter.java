package com.example.rbcassignment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcassignment.R;
import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.viewmodels.BankAccountViewModel;

import java.util.List;

public class AccountRVAdapter extends RecyclerView.Adapter<AccountRVAdapter.AccountViewHolder> {

    private final List<BankAccount> accountList;
    private OnAccountClickListener listener;

    public AccountRVAdapter(List<BankAccount> accountList) {
        this.accountList = accountList;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        BankAccount account = this.accountList.get(position);
        holder.bind(account);
    }

    @Override
    public int getItemCount() {
        return this.accountList.size();
    }

    public void setOnClickListener(OnAccountClickListener listener) {
        this.listener = listener;
    }

    /**
     * Used to initialize the views in the recycler view
     */
    class AccountViewHolder extends RecyclerView.ViewHolder {

        private final TextView accountNameTv;
        private final TextView accountNumTv;
        private final TextView accountBalanceTv;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            this.accountNameTv = itemView.findViewById(R.id.item_title_tv);
            this.accountNumTv = itemView.findViewById(R.id.item_subtitle_tv);
            this.accountBalanceTv = itemView.findViewById(R.id.item_amount_tv);
        }

        /**
         * Set values to views and the view holders onClickListener
         */
        public void bind(BankAccount account) {
            this.accountNameTv.setText(account.getAccountDetails().getName());
            this.accountNumTv.setText(account.getAccountDetails().getNumber());
            this.accountBalanceTv.setText(BankAccountViewModel
                                 .formatBalance(account.getAccountDetails().getBalance()));
            itemView.setOnClickListener(view -> listener.onAccountClick(account));
        }
    }

    public interface OnAccountClickListener {
        void onAccountClick(BankAccount account);
    }
}
