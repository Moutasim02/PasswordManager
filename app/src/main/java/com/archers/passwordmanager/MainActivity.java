package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create a new instance of the fragment
        VaultFragment myFragment = new VaultFragment();

        // Add the fragment to the container
        fragmentTransaction.add(R.id.fragment_container, myFragment);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}