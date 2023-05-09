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

import java.util.ArrayList;

public class VaultFragment extends Fragment {
    private ArrayList<VaultItem> vaultItems;

    public VaultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vaultItems = new ArrayList<>();
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

        // Create a new VaultItem and add it to the list
        // Create 15 new VaultItem objects
        VaultItem item1 = new VaultItem("Item 1");
        VaultItem item2 = new VaultItem("Item 2");
        VaultItem item3 = new VaultItem("Item 3");
        VaultItem item4 = new VaultItem("Item 4");
        VaultItem item5 = new VaultItem("Item 5");
        VaultItem item6 = new VaultItem("Item 6");
        VaultItem item7 = new VaultItem("Item 7");
        VaultItem item8 = new VaultItem("Item 8");
        VaultItem item9 = new VaultItem("Item 9");
        VaultItem item10 = new VaultItem("Item 10");

        // Add the 15 VaultItem objects to the vaultItems ArrayList
        vaultItems.add(item1);
        vaultItems.add(item2);
        vaultItems.add(item3);
        vaultItems.add(item4);
        vaultItems.add(item5);
        vaultItems.add(item6);
        vaultItems.add(item7);
        vaultItems.add(item8);
        vaultItems.add(item9);
        vaultItems.add(item10);

        // Inflate the item_vault.xml layout for each item in the list and add it to the LinearLayout
        for (VaultItem item : vaultItems) {
            View itemLayout = inflater.inflate(R.layout.item_vault, vaultLayout, false);
            TextView itemName = itemLayout.findViewById(R.id.vaultItemName);
            TextView itemDate = itemLayout.findViewById(R.id.vaultItemDate);
            itemName.setText(item.getName());
            itemDate.setText(item.getDate());
            vaultLayout.addView(itemLayout);
        }
        return view;
    }

    private void showFragment(Fragment addNewItemFragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addNewItemFragment)
                .commit();
    }
}