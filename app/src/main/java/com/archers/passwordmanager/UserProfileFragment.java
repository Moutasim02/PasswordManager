package com.archers.passwordmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class UserProfileFragment extends Fragment {
    FirebaseAuth auth;
    TextInputEditText editTextName, editTextEmail, editTextNewPassword, editTextCurrentPassword;
    String userName, userEmail;
    Button buttonSaveProfile;

    public UserProfileFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        userName = auth.getCurrentUser().getDisplayName();
        userEmail = auth.getCurrentUser().getEmail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        buttonSaveProfile = view.findViewById(R.id.buttonSaveProfile);
        editTextName = view.findViewById(R.id.editTextName);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);
        editTextName.setText(userName);
        editTextEmail.setText(userEmail);

        buttonSaveProfile.setOnClickListener((khaled) -> {
            FirebaseUser user = auth.getCurrentUser(); // used to make specific user profile and data
            UserProfileChangeRequest upc = new UserProfileChangeRequest.Builder()
                    .setDisplayName(String.valueOf(editTextName.getText()))
                    .build();

            if (!user.getDisplayName().equals(String.valueOf(editTextName.getText()))) {
                user.updateProfile(upc);
                Toast.makeText(getContext(), "Username Updated!", Toast.LENGTH_SHORT).show();
            }

            if (editTextCurrentPassword.getText().length() != 0 && editTextNewPassword.getText().length() == 0) {
                Toast.makeText(getContext(), "Add new password!", Toast.LENGTH_SHORT).show();
            }

            if (String.valueOf(editTextCurrentPassword.getText()).equals(String.valueOf(editTextNewPassword.getText()))) {
                Toast.makeText(getContext(), "Same Password!", Toast.LENGTH_SHORT).show();
            } else if (editTextCurrentPassword.getText().length() != 0 && editTextNewPassword.getText().length() != 0) {
                user.updatePassword(String.valueOf(editTextNewPassword.getText()));
                Toast.makeText(getContext(), "Password Updated!", Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

        });
        return view;
    }
}