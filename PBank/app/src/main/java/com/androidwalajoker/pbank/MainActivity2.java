package com.androidwalajoker.pbank;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//This is the activity where fragments are used to create multiple tabs such as balance, debit, credit.
public class MainActivity2 extends AppCompatActivity {
    Button balanceButton, debitButton, creditButton;
    FragmentManager fragmentManager;
    private FragmentType currentFragment;
    public enum FragmentType {
        CHECK_BALANCE,
        DEBIT,
        CREDIT
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PranavTag" , "I am in Activity2");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        fragmentManager = getSupportFragmentManager();
        balanceButton = findViewById(R.id.buttonBalance);
        creditButton = findViewById(R.id.buttonCredit);
        debitButton = findViewById(R.id.buttonDebit);
        currentFragment = FragmentType.CHECK_BALANCE;
        //This is how we access data(Account_Number) passed from activity 1 to activity 2.
        String AccountNo = getIntent().getStringExtra(MainActivity.balanceKey);

        balanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment != FragmentType.CHECK_BALANCE) {
                    CheckBalance checkBalanceFragment = new CheckBalance();
                    Bundle bundle = new Bundle();
                    bundle.putString(MainActivity.balanceKey, AccountNo);
                    checkBalanceFragment.setArguments(bundle);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    if (currentFragment.ordinal() < FragmentType.CHECK_BALANCE.ordinal()) {
                        transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
                    } else {

                        transaction.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                    }

                    transaction.replace(R.id.fragmentContainerView, checkBalanceFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();

                    currentFragment = FragmentType.CHECK_BALANCE;
                }
            }
        });

        debitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment != FragmentType.DEBIT) {
                    Deposit depositFragment = new Deposit();
                    Bundle bundle = new Bundle();
                    bundle.putString(MainActivity.balanceKey, AccountNo);
                    depositFragment.setArguments(bundle);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    if (currentFragment.ordinal() < FragmentType.DEBIT.ordinal()) {
                        transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
                    } else {
                        transaction.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                    }
                    transaction.replace(R.id.fragmentContainerView, depositFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();

                    currentFragment = FragmentType.DEBIT;
                }
            }
        });

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment != FragmentType.CREDIT) {
                    Credit creditFragment = new Credit();
                    Bundle bundle = new Bundle();
                    bundle.putString(MainActivity.balanceKey, AccountNo);
                    creditFragment.setArguments(bundle);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    if (currentFragment.ordinal() < FragmentType.CREDIT.ordinal()) {
                        transaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left);
                    } else {
                        transaction.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right);
                    }
                    transaction.replace(R.id.fragmentContainerView, creditFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                    currentFragment = FragmentType.CREDIT;
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}