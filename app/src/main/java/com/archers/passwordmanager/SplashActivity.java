package com.archers.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();
        // Check if user is already logged in
        FirebaseUser user = auth.getCurrentUser();
        Intent intent;
        if (user != null) {
            // User is already logged in, navigate to MainActivity
            intent = new Intent(this, MainActivity.class);
        } else {
            // User is not logged in, navigate to SignUpActivity
            intent = new Intent(this, SignUpActivity.class);
        }
        startActivity(intent);
        finish();
    }
}