package com.archers.passwordmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {
    FirebaseAuth auth;
    TextView signOutNow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        MaterialSwitch darkSwitch = view.findViewById(R.id.dark_switch);

        // Set the initial state of the switch based on the current mode
        int currentNightMode = AppCompatDelegate.getDefaultNightMode();
        darkSwitch.setChecked(currentNightMode == AppCompatDelegate.MODE_NIGHT_YES);

        // Handle switch state changes
        darkSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int newNightMode = isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
            AppCompatDelegate.setDefaultNightMode(newNightMode);
            MainActivity.navigationView.setSelectedItemId(R.id.my_vault);
        });

        signOutNow = view.findViewById(R.id.sign_out_now);
        signOutNow.setOnClickListener((v) -> {
            auth.signOut();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        return view;
    }
}