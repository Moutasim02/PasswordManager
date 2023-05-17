package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VaultFragment extends Fragment {
    private Map<String, Object> vaultItems;
    FirebaseFirestore db;

    public VaultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vaultItems = new HashMap<>();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_vault, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabAddItem);
        floatingActionButton.setOnClickListener(v -> {
            AddNewItemFragment addNewItemFragment = new AddNewItemFragment();
            showFragment(addNewItemFragment);
        });

        // Get the LinearLayout that will hold the vault items
        LinearLayout vaultLayout = view.findViewById(R.id.vaultLayout);

        return view;
    }

    private void showFragment(Fragment addNewItemFragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addNewItemFragment)
                .commit();
    }
}