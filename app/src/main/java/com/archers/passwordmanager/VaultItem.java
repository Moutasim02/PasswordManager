package com.archers.passwordmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VaultItem {
    private String siteName, url, creationDate;

    public VaultItem(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getCreationDate() {
        Date tempDate = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        creationDate = DateFor.format(tempDate);
        return creationDate;
    }
}