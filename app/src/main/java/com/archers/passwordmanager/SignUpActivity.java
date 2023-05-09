package com.archers.passwordmanager;

import androidx.annotation.NonNull;
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

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextInputLayout usernameTextField, confirmPasswordTextField, passwordTextField, emailTextField;
    private Button signUpButton;
    private TextView signInInstead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        usernameTextField = findViewById(R.id.usernameTextField);
        emailTextField = findViewById(R.id.emailTextField);
        passwordTextField = findViewById(R.id.passwordTextField);
        confirmPasswordTextField = findViewById(R.id.confirmPasswordTextField);
        signUpButton = findViewById(R.id.signUpButton);
        signInInstead = findViewById(R.id.sign_in_instead);

        signInInstead.setOnClickListener((v) -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        signUpButton.setOnClickListener((v) -> {
            String username = String.valueOf(usernameTextField.getEditText().getText());
            String email = String.valueOf(emailTextField.getEditText().getText());
            String password = String.valueOf(passwordTextField.getEditText().getText());
            String confirmPassword = String.valueOf(confirmPasswordTextField.getEditText().getText());

            boolean userInputValidated =
                    validateUserInput(username, email, password, confirmPassword);

            if (userInputValidated)
                createUserAccount(email, password);
        });



    }

    private boolean validateUserInput(String username, String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(username)) {
            usernameTextField.setError("Please enter a username");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            emailTextField.setError("Please enter an email address");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordTextField.setError("Please enter a password");
            return false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordTextField.setError("Please confirm your password");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordTextField.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createUserAccount(String email, String password) {
        // Create a new user with the email and password
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                FirebaseUser user = auth.getCurrentUser(); // used to make specific user profile and data

                Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                // TODO: Add code to update the UI or navigate to the next screen
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                if (user != null) {
                    intent.putExtra("userMail", user.getEmail());
                }
                startActivity(intent);
                finish();
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(SignUpActivity.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}