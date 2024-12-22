package com.androidwalajoker.pbank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    public static final String balanceKey = "androidx.core.view.WindowInsetsCompat.BALANCE";

    EditText loginAccountText;
    EditText loginPasswordText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loginAccountText = findViewById(R.id.loginAccount);
        loginPasswordText = findViewById(R.id.loginPassword);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void createAccount(View view){
        startActivity(new Intent(MainActivity.this, MainActivity3.class ));
    }
    public void Login(View view){
        String loginAccount = loginAccountText.getText().toString();
        String loginPassword = loginPasswordText.getText().toString();

        if (loginAccount.isEmpty() || loginPassword.isEmpty()) {
            Toast.makeText(this, "Please enter both account number and password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (authentication(loginAccount, loginPassword)){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra(balanceKey, loginAccount);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong Account No. or Password :(", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean authentication(String accountNo, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("Pranav", MODE_PRIVATE);
        String storedPassword = sharedPreferences.getString("account_" + accountNo + "_password", null);
        if (storedPassword == null) {
            return false;
        }
        return password.equals(storedPassword);
    }
}