package com.example.rbcassignment.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rbcassignment.R;
import com.example.rbcassignment.model.BankAccount;
import com.example.rbcassignment.model.BankAccountProvider;
import com.example.rbcassignment.model.CreditCardBankAccount;
import com.rbc.rbcaccountlibrary.Account;
import com.rbc.rbcaccountlibrary.AccountProvider;
import com.rbc.rbcaccountlibrary.Transaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TESTING
        BankAccountProvider a = new BankAccountProvider();
        List<BankAccount> c = null;
        try {
            c = a.getChequingAccountList();
            Log.i("CHEQUING", c.toString());
            Log.i("CHEQUING TRANS", c.get(0).getTransactionList().toString());
            List<CreditCardBankAccount> cc = a.getCreditCardAccountList();
            Log.i("CC", cc.toString());
            Log.i("CC TRANS", cc.get(0).getAdditionalTransactionList().toString());
//            List<Transaction> trans = AccountProvider.INSTANCE.getTransactions(c.get(0).getAccount().getNumber());
//            Log.i("Trans", trans.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            BankAccountProvider a = new BankAccountProvider();
//            List<BankAccount> c = a.getChequingAccountList();
//            Log.i("CHEQUING", c.toString());
////            List<CreditCardBankAccount> d = a.getCreditCardAccountList();
////            Log.i("CREDIT", d.get(0).getTransactionList().get(0).getDescription());
////            Log.i("CREDIT ADD", d.get(0).getAdditionalTransactionList().get(0).getDescription());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}