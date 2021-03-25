package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IncomingCallDataModel {

    @SerializedName("IncomingCallData")
    @Expose
    private List<IncomingCallAdData> incomingCallData = null;

    public List<IncomingCallAdData> getIncomingCallData() {
        return incomingCallData;
    }

    public void setIncomingCallData(List<IncomingCallAdData> incomingCallData) {
        this.incomingCallData = incomingCallData;
    }
}
