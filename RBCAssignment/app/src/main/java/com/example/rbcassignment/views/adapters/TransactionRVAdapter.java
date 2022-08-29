package com.example.rbcassignment.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbcassignment.R;
import com.example.rbcassignment.viewmodels.AccountDetailViewModel;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class TransactionRVAdapter extends RecyclerView.Adapter<TransactionRVAdapter.TransactionViewHolder>{

    private final List<Transaction> transactionList;

    public TransactionRVAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new TransactionRVAdapter.TransactionViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        if (!this.transactionList.isEmpty()) {
            Transaction transaction = this.transactionList.get(position);
            holder.bind(transaction);
        }
    }

    @Override
    public int getItemCount() {
        return this.transactionList.size();
    }

    /**
     * Initializes views in transaction list item
     */
    class TransactionViewHolder extends RecyclerView.ViewHolder {

        private final TextView descriptionTv;
        private final TextView dateTv;
        private final TextView amountTv;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.descriptionTv = itemView.findViewById(R.id.item_title_tv);
            this.dateTv = itemView.findViewById(R.id.item_subtitle_tv);
            this.amountTv = itemView.findViewById(R.id.item_amount_tv);
        }

        public void bind(Transaction trans) {
            this.descriptionTv.setText(trans.getDescription());
            this.dateTv.setText(AccountDetailViewModel.formatDate(trans.getDate()));
            this.amountTv.setText(AccountDetailViewModel.formatAmount(trans.getAmount()));
        }
    }

}
