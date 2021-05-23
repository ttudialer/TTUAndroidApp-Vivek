package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LibraryGetModel {
    @SerializedName("isProfile")
    @Expose
    private String isProfile;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("fileUrl")
    @Expose
    private String fileUrl;

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("__v")
    @Expose
    private String _v;

    public String getIsProfile() {
        return isProfile;
    }
    public void setIsProfile(String isProfile) {
        this.isProfile = isProfile;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {this.fileUrl = fileUrl; }


    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getV() {
        return _v;
    }
    public void setV(String _v) {
        this._v = _v;
    }


}
