package com.archers.passwordmanager;

import static android.widget.Toast.LENGTH_LONG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddNewItemFragment extends Fragment {
    FirebaseFirestore db;
    // ToDo: Add item to FireStore
    // Each User has a collection, item will be stored in a document.
    // document contains fields, ex: email, name, pass, domain.

    public AddNewItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextInputLayout textInputLayoutAddName, textInputLayoutUrl, textInputLayoutEmail, textInputLayoutPassword;
        Button buttonSaveItem;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);
        textInputLayoutAddName = view.findViewById(R.id.textInputLayoutAddName);
        textInputLayoutUrl = view.findViewById(R.id.textInputLayoutUrl);
        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = view.findViewById(R.id.textInputLayoutPassword);
        buttonSaveItem = view.findViewById(R.id.buttonSaveProfile);

        buttonSaveItem.setOnClickListener((v) -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
            Toast.makeText(getActivity(), "Item Saved", LENGTH_LONG).show();
        });
        return view;
    }
}