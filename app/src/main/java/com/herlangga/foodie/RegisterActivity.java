package com.herlangga.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditText,emailEditText,passwordEditText,confirmPasswordEditText,fullnameEditText;

    RadioGroup genderRadioGrup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        fullnameEditText = findViewById(R.id.fullNameEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        genderRadioGrup = findViewById(R.id.genderRadioGrup);
    }

    public void register(View view) {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirm = confirmPasswordEditText.getText().toString();
        String fullname = fullnameEditText.getText().toString();
        int selectedGenderId = genderRadioGrup.getCheckedRadioButtonId();
        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String gender = selectedGenderRadioButton.getText().toString();

        SharedPreferences sharedPreferences = this.getSharedPreferences("ABC_SHAREDPREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ANGGA", username);
        editor.putString("ABC_EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.putString("CONFIRM", confirm);
        editor.putString("FULLNAME", fullname);
        editor.putString("GENDER", gender);
        editor.apply();

        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);

    }
}