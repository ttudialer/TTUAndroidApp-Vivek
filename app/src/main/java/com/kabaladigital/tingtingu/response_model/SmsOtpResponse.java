package com.kabaladigital.tingtingu.response_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsOtpResponse {
   @SerializedName("message")
   @Expose
   private String message;

    @SerializedName("type")
    @Expose
    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
