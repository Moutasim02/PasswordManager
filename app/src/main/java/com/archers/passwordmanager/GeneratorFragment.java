package com.archers.passwordmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GeneratorFragment extends Fragment {
    GeneratePassword generatePassword;

    public GeneratorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        generatePassword = new GeneratePassword(/*Parameters here*/);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generator, container, false);
    }
}