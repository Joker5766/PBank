package com.androidwalajoker.pbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Deposit extends Fragment {
    EditText amountText;
    Button confirmButton;
    String accountNo;
    TextView showStatus;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deposit, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            accountNo = bundle.getString(MainActivity.balanceKey);
        }

        amountText = view.findViewById(R.id.creditText);
        confirmButton = view.findViewById(R.id.buttonConfirm2);
        showStatus = view.findViewById(R.id.showText);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amountText.getText().toString();
                if (!amountStr.isEmpty()) {
                    int amount = Integer.parseInt(amountStr);
                    depositAmount(amount);
                }
            }
        });

        return view;
    }

    private void depositAmount(int amount) {
        if (accountNo != null) {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("Pranav", Context.MODE_PRIVATE);
            String balanceKey = "account_" + accountNo + "_balance";
            int currentBalance = Integer.parseInt(sharedPreferences.getString(balanceKey, "0"));
            int newBalance = currentBalance + amount;
            sharedPreferences.edit().putString(balanceKey, String.valueOf(newBalance)).apply();

            showStatus.setText("Deposited: " + amount + ". New Balance: " + newBalance);
        }
        else {
            showStatus.setText("Something Went Wrong!!");
        }
    }
}
