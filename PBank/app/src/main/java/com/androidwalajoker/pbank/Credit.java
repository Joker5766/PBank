package com.androidwalajoker.pbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Credit extends Fragment {
    EditText creditAmount;
    Button creditButton;
    String accountNo;
    TextView showText;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            accountNo = bundle.getString(MainActivity.balanceKey);
        }

        creditAmount = view.findViewById(R.id.creditAmountText);
        creditButton = view.findViewById(R.id.buttonCredit2);
        showText = view.findViewById(R.id.showText2);

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accountNo != null) {
                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("Pranav", Context.MODE_PRIVATE);
                    String balanceKey = "account_" + accountNo + "_balance";
                    int currentBalance = Integer.parseInt(sharedPreferences.getString(balanceKey, "0"));
                    int enteredAmt = Integer.parseInt(creditAmount.getText().toString());

                    if (enteredAmt > currentBalance) {
                        showText.setText("Insufficient Balance");
                    } else {
                        creditAmount(enteredAmt);
                    }
                } else {
                    showText.setText("Account number not found.");
                }
            }
        });

        return view;
    }

    private void creditAmount(int amount) {
        if (accountNo != null) {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("Pranav", Context.MODE_PRIVATE);
            String balanceKey = "account_" + accountNo + "_balance";
            int currentBalance = Integer.parseInt(sharedPreferences.getString(balanceKey, "0"));
            int newBalance = currentBalance - amount;
            sharedPreferences.edit().putString(balanceKey, String.valueOf(newBalance)).apply();
            showText.setText("Credited: " + amount + ". New Balance: " + newBalance);
        } else {
            showText.setText("Something Went Wrong:(");
        }
    }
}
