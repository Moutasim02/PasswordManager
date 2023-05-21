package com.archers.passwordmanager;

import java.io.Serializable;

public class VaultItem implements Serializable {
    private String siteName, url, creationDate, mailOrUsername, password;

    public VaultItem() {
    }

    public VaultItem(String siteName, String url, String creationDate) {
        this.siteName = siteName;
        this.url = url;
        this.creationDate = creationDate;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getUrl() {
        return url;
    }

    public String getMailOrUsername() {
        return mailOrUsername;
    }

    public void setMailOrUsername(String mailOrUsername) {
        this.mailOrUsername = mailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}