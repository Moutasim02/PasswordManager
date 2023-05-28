package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditItemFragment extends Fragment {
    private VaultItem item;
    private FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        if (getArguments() != null) {
            item = (VaultItem) getArguments().getSerializable("item");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextInputLayout siteNameET, userNameET, passET, domainET;
        Button saveBtn;

        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);

        siteNameET = view.findViewById(R.id.NameET);
        userNameET = view.findViewById(R.id.UserNameET);
        passET = view.findViewById(R.id.passET);
        domainET = view.findViewById(R.id.domainET);
        saveBtn = view.findViewById(R.id.saveBtn);
        siteNameET.getEditText().setText(item.getSiteName());
        userNameET.getEditText().setText(item.getMailOrUsername());
        domainET.getEditText().setText(item.getUrl());

        String decipheredPassword;
        try {
            decipheredPassword = TextEncryption.decrypt(item.getPassword());
            passET.getEditText().setText(decipheredPassword);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error while decrypting your password!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }

        saveBtn.setOnClickListener((v) -> {
            Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
        });

        TextView deleteItem = view.findViewById(R.id.deleteItemText);
        deleteItem.setOnClickListener((v) -> {
            Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}