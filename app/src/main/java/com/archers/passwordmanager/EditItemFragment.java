package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class EditItemFragment extends Fragment {
    private VaultItem item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (VaultItem) getArguments().getSerializable("item");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextInputLayout siteNameET, userNameET, passET, domainET;
        Button saveBtn;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_item, container, false);

        siteNameET = view.findViewById(R.id.NameET);
        userNameET = view.findViewById(R.id.UserNameET);
        passET = view.findViewById(R.id.passET);
        domainET = view.findViewById(R.id.domainET);
        saveBtn = view.findViewById(R.id.saveBtn);

        siteNameET.getEditText().setText(item.getSiteName());
        userNameET.getEditText().setText(item.getMailOrUsername());
        passET.getEditText().setText(item.getPassword());
        domainET.getEditText().setText(item.getUrl());

        saveBtn.setOnClickListener((v) -> {
            // TODO: Save the edited VaultItem object
        });

        return view;
    }
}