package com.archers.passwordmanager;

import android.icu.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VaultItem {
    private String name;
    private String date;

    public VaultItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        Date tempDate = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        date = DateFor.format(tempDate);
        return date;
    }
}