package com.archers.passwordmanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        navigationView = findViewById(R.id.bottom_navigation);
        addNavBarListener();
    }

    private void addNavBarListener() {
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment dynamicFragment = null;

                if (id == R.id.my_vault) {
                    dynamicFragment = new VaultFragment();
                    floatingActionButton.setVisibility(View.VISIBLE);
                } else if (id == R.id.generator) {
//                    dynamicFragment = new GeneratorFragment();
                    floatingActionButton.setVisibility(View.INVISIBLE);
                } else if (id == R.id.settings) {
//                    dynamicFragment = new SettingsFragment();
                    floatingActionButton.setVisibility(View.INVISIBLE);
                } else if (id == R.id.profile) {
//                    dynamicFragment = new ProfileFragment();
                    floatingActionButton.setVisibility(View.INVISIBLE);
                }

                if (dynamicFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, dynamicFragment)
                            .commit();
                }
                return true;
            }
        });
    }
}
