package com.archers.passwordmanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView navigationView;
    MaterialToolbar topAppBar;
    Fragment dynamicFragment;
    MenuItem searchItem, syncItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_navigation);
        topAppBar = findViewById(R.id.topAppBar);
        searchItem = topAppBar.getMenu().findItem(R.id.search);
        syncItem = topAppBar.getMenu().findItem(R.id.sync);

        // Default Fragment Injector
        Fragment dynamicFragment = new VaultFragment();
        showFragment(dynamicFragment);

        addNavBarListener();
    }


    private void addNavBarListener() {
        navigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.my_vault) {
                dynamicFragment = new VaultFragment();
                changeTopBarItemsVisibility(true);
            } else if (id == R.id.generator) {
                dynamicFragment = new GeneratorFragment();
                changeTopBarItemsVisibility(false);
            } else if (id == R.id.settings) {
                dynamicFragment = new SettingsFragment();
                changeTopBarItemsVisibility(false);
            } else if (id == R.id.profile) {
                dynamicFragment = new UserProfileFragment();
                changeTopBarItemsVisibility(false);
            }

            if (dynamicFragment != null)
                showFragment(dynamicFragment);
            return true;
        });
    }

    private void changeTopBarItemsVisibility(boolean showIcons) {
        searchItem.setVisible(showIcons);
        syncItem.setVisible(showIcons);
    }

    private void showFragment(Fragment dynamicFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, dynamicFragment)
                .commit();
    }
}
