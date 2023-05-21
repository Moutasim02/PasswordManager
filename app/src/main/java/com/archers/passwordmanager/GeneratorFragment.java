package com.archers.passwordmanager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class GeneratorFragment extends Fragment {
    PasswordGenerator passwordGenerator;

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
        View view = inflater.inflate(R.layout.fragment_generator, container, false);

        Slider slider = view.findViewById(R.id.slider);
        CheckBox uppercase_checkbox = view.findViewById(R.id.uppercase_checkbox);
        CheckBox lowercase_checkbox = view.findViewById(R.id.lowercase_checkbox);
        CheckBox numbers_checkbox = view.findViewById(R.id.numbers_checkbox);
        CheckBox symbols_checkbox = view.findViewById(R.id.symbols_checkbox);
        TextView passTxt = view.findViewById(R.id.password_text);
        TextView passLengthTxt = view.findViewById(R.id.length_text);
        ImageButton passCopyBtn = view.findViewById(R.id.PassCopyBtn);

        Button generate_button = view.findViewById(R.id.generate_button);

        passCopyBtn.setOnClickListener((v) -> {
            if (!String.valueOf(passTxt.getText()).equals("Your password will appear here") && !String.valueOf(passTxt.getText()).equals("Invalid password configuration."))
                setClipboard(getContext(), String.valueOf(passTxt.getText()));
            else
                Toast.makeText(getContext(), "Generate Password First!", Toast.LENGTH_SHORT).show();
        });

       slider.addOnChangeListener(new Slider.OnChangeListener() {
           @Override
           public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
               passLengthTxt.setText(String.valueOf((int) value));
           }
       });

        generate_button.setOnClickListener((v) -> {
            int passLength = (int) slider.getValue();
            boolean uppercase, lowercase, numbers, symbols;
            uppercase = uppercase_checkbox.isChecked();
            lowercase = lowercase_checkbox.isChecked();
            numbers = numbers_checkbox.isChecked();
            symbols = symbols_checkbox.isChecked();

            passwordGenerator = new PasswordGenerator(passLength, uppercase, lowercase, numbers, symbols);
            String password = passwordGenerator.generatePassword();
            passTxt.setText(password);
        });

        return view;
    }

    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        clipboard.setPrimaryClip(clip);
    }
}