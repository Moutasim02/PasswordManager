package com.archers.passwordmanager;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewItemFragment extends Fragment {

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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_item, container, false);

        TextView siteName = view.findViewById(R.id.siteName);
        TextView emailOrUsername = view.findViewById(R.id.emailOrUsernameTV);
        TextView passTV = view.findViewById(R.id.passTV);
        TextView domainTV = view.findViewById(R.id.domainTV);
        MaterialButton passCopyBtn = view.findViewById(R.id.PassCopyBtn);

        // Set the text of the TextViews to the values of the VaultItem object
        siteName.setText(item.getSiteName());
        emailOrUsername.setText(item.getMailOrUsername());

        String decipheredPassword;
        try {
            decipheredPassword = TextEncryption.decrypt(item.getPassword());
            passTV.setText(decipheredPassword);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error while decrypting your password!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
        domainTV.setText(item.getUrl());

        TextView deleteItem = view.findViewById(R.id.deleteItemTV);
        deleteItem.setOnClickListener((v) -> {
            Toast.makeText(getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
        });

        passCopyBtn.setOnClickListener((v) -> {
            setClipboard(getContext(), String.valueOf(passTV.getText()));
            Toast.makeText(getContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        FloatingActionButton editFloatingActionButton = view.findViewById(R.id.floatingActionButton);

        editFloatingActionButton.setOnClickListener((v) -> {
            Fragment fragment = new EditItemFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", item);
            fragment.setArguments(bundle);
            showFragment(fragment);
        });

        return view;
    }

    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }

    private void showFragment(Fragment addNewItemFragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addNewItemFragment)
                .addToBackStack(null)
                .commit();
    }
}