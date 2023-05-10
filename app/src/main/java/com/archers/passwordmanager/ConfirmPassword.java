package com.archers.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ConfirmPassword extends AppCompatActivity {

    private TextInputLayout passwordTextField, confirmPasswordTextField;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        passwordTextField = findViewById(R.id.PasswordTextField);
        confirmPasswordTextField = findViewById(R.id.ConfirmpasswordTextField);

        saveButton.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }
}