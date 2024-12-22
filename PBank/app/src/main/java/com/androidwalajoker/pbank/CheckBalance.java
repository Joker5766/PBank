package com.androidwalajoker.pbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CheckBalance extends Fragment {

    private Button refreshButton;
    private TextView balanceText;

    private String accountNo;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_balance, container, false);

        refreshButton = view.findViewById(R.id.buttonRefresh);
        balanceText = view.findViewById(R.id.showBalance);
        Bundle bundle = getArguments();
        if (bundle != null) {
            accountNo = bundle.getString(MainActivity.balanceKey);
        }

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBalance();
            }
        });

        updateBalance();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateBalance();
    }

    private String getBalance(String accountNo) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Pranav", Context.MODE_PRIVATE);
        String BalanceKey = "account_" + accountNo + "_balance";
        return sharedPreferences.getString(BalanceKey, "ERROR:("); // Default to "0" if the key is not found
    }

    @SuppressLint("SetTextI18n")
    private void updateBalance() {
        if (accountNo != null) {
            String currentBalance = getBalance(accountNo);
            balanceText.setText("Balance: " + currentBalance);

        } else {
            balanceText.setText("Balance: Not available");
        }
    }
}
