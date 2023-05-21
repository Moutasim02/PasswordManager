package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VaultFragment extends Fragment {
    FirebaseFirestore db;
    private ArrayList<VaultItem> vaultItems;
    private String username;
    private FirebaseAuth auth;

    public VaultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getDisplayName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vaultItems = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_vault, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabAddItem);
        floatingActionButton.setOnClickListener(v -> {
            AddNewItemFragment addNewItemFragment = new AddNewItemFragment();
            showFragment(addNewItemFragment);
        });

        // Get the LinearLayout that will hold the vault items
        LinearLayout vaultLayout = view.findViewById(R.id.vaultLayout);

        // Retrieve the vault items from Firestore
        db.collection(username).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        VaultItem item = document.toObject(VaultItem.class);
                        vaultItems.add(item);
                    }

                    // Add the vault items to the layout
                    for (VaultItem item : vaultItems) {
                        View itemLayout = inflater.inflate(R.layout.item_vault, vaultLayout, false);
                        TextView itemName = itemLayout.findViewById(R.id.vaultItemName);
                        TextView itemURL = itemLayout.findViewById(R.id.vaultItemDomain);
                        TextView itemDate = itemLayout.findViewById(R.id.vaultItemDate);

                        itemName.setText(item.getSiteName());
                        itemURL.setText(item.getUrl());
                        itemDate.setText(item.getCreationDate());

                        itemLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ViewItemFragment viewItemFragment = new ViewItemFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("item", item);
                                viewItemFragment.setArguments(bundle);
                                showFragment(viewItemFragment);
                            }
                        });

                        vaultLayout.addView(itemLayout);
                    }
                } else {
                    Toast.makeText(getContext(), "Error getting documents: ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void showFragment(Fragment addNewItemFragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addNewItemFragment)
                .addToBackStack(null)
                .commit();
    }
}

