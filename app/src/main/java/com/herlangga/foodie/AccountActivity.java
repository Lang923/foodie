package com.herlangga.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    TextView usernameTextView, emailTextView, passwordTextView, fullnameTextView, genderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        fullnameTextView = findViewById(R.id.fullnameTextView);
        genderTextView = findViewById(R.id.genderTextView);

        SharedPreferences sharedPreferences = this.getSharedPreferences("ABC_SHAREDPREF", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("ANGGA", "");
        String email = sharedPreferences.getString("ABC_EMAIL", "");
        String password = sharedPreferences.getString("PASSWORD", "");
        String fullname = sharedPreferences.getString("FULLNAME", "");
        String gender = sharedPreferences.getString("GENDER", "");

        usernameTextView.setText(username);
        emailTextView.setText(email);
        passwordTextView.setText(password);
        fullnameTextView.setText(fullname);
        genderTextView.setText(gender);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_account) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
