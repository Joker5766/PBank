package com.androidwalajoker.pbank;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private EditText userNameText;
    private EditText userBalanceText;
    private EditText userAccountText;
    private EditText userPasswordText;
    private Button saveButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        userNameText = findViewById(R.id.userName);
        userAccountText = findViewById(R.id.userAccount);
        userPasswordText = findViewById(R.id.userPassword);
        userBalanceText = findViewById(R.id.userBalance);
        saveButton = findViewById(R.id.createAccountBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAccount();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void saveAccount(){
        //All entered data is taken and converted into string and then stored in their respective variables.
        //This saved variables are used to store the data in mobiles storage through sharedPreferences.
        String userName = userNameText.getText().toString();
        String userPassword = userPasswordText.getText().toString();
        String userAccountNo = userAccountText.getText().toString();
        String userBalance = userBalanceText.getText().toString();

        if(userName.isEmpty() || userPassword.isEmpty() || userAccountNo.isEmpty() || userBalance.isEmpty()){
            //If Anything is empty then error message will appear.
            Toast.makeText(this, "Enter Complete Details", Toast.LENGTH_SHORT).show();
            return;
        }

        //This is how data is stored in devices memory using sharedPreferences.
        SharedPreferences sharedPreferences = getSharedPreferences("Pranav", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("account_"+userAccountNo+"_username",userName);
        editor.putString("account_"+userAccountNo+"_accountNo",userAccountNo);
        editor.putString("account_"+userAccountNo+"_password",userPassword);
        editor.putString("account_"+userAccountNo+"_balance",userBalance);
        editor.apply();

        Toast.makeText(this, "Account Created Successfully :)", Toast.LENGTH_SHORT).show();
        finish();
    }
}