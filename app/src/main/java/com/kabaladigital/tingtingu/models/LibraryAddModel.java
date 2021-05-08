package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryAddModel {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("isProfile")
    @Expose
    private String isProfile;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsProfile() {
        return isProfile;
    }

    public void setIsProfile(String isProfile) {
        this.isProfile = isProfile;
    }

}
