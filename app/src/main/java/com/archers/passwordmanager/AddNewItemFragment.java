package com.archers.passwordmanager;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNewItemFragment extends Fragment {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    // ToDo: Add item to FireStore
    // Each User has a collection, item will be stored in a document.
    // document contains fields, ex: email, name, pass, domain.
    private String username;
    private TextInputEditText editTextAddName, editTextUrl, editTextEmailorUsername, editTextPassword;

    public AddNewItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getDisplayName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button buttonSaveItem;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_item, container, false);

        editTextAddName = view.findViewById(R.id.editTextAddName);
        editTextUrl = view.findViewById(R.id.editTextUrl);
        editTextEmailorUsername = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonSaveItem = view.findViewById(R.id.buttonSaveProfile);



        buttonSaveItem.setOnClickListener((v) -> {

            String siteName = String.valueOf(editTextAddName.getText());
            String url = String.valueOf(editTextUrl.getText());
            String mailOrUsername = String.valueOf(editTextEmailorUsername.getText());
            String password = String.valueOf(editTextPassword.getText());
            String date;
            Date tempDate = new Date();
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            date = DateFor.format(tempDate);

            Map<String, Object> item = new HashMap<>();
            item.put("siteName", siteName);
            item.put("url", url);
            item.put("mailOrUsername", mailOrUsername);
            item.put("password", password);
            item.put("creationDate", date);

            boolean inputValidated = validateUserInput(siteName, url, mailOrUsername, password);

            if (inputValidated) {
                db.collection(username).document().set(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Item Saved", LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error while adding item", LENGTH_LONG).show();
                            }
                        });
            } else return;


            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }

    private boolean validateUserInput(String name, String url, String email, String password) {
        if (name.isEmpty()) {
            editTextAddName.setError("No username inserted");
            return false;
        }
        if (url.isEmpty()) {
            editTextUrl.setError("No url inserted");
            return false;
        }
        if (email.isEmpty()) {
            editTextEmailorUsername.setError("No email inserted");
            return false;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("No password inserted");
            return false;
        }
        return true;
    }


}