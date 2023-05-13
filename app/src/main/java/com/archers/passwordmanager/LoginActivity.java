package com.archers.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button signInButton;
    private TextView signUpInstead, forgetPassword;
    private TextInputLayout emailTextField, passwordTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        emailTextField = findViewById(R.id.emailTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        signInButton = findViewById(R.id.loginButton);
        signUpInstead = findViewById(R.id.sign_up_instead);
        forgetPassword = findViewById(R.id.forgotPasswordText);


        signUpInstead.setOnClickListener((v) -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        signInButton.setOnClickListener((v) -> {
            String email = String.valueOf(emailTextField.getEditText().getText());
            String password = String.valueOf(passwordTextField.getEditText().getText());

            boolean userInputValidated =
                    validateUserInput(email, password);

            if (userInputValidated) {
                signInToAccount(email, password);
            }
        });

        forgetPassword.setOnClickListener((v) -> {
            Intent intent = new Intent(this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

    }

    private void signInToAccount(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                // Navigate to MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(LoginActivity.this, "Sign in failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userMail = extras.getString("userMail");
            emailTextField.getEditText().setText(userMail);
        }
    }

    private boolean validateUserInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            emailTextField.setError("Please enter an email address");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordTextField.setError("Please enter a password");
            return false;
        }
        return true;
    }
}