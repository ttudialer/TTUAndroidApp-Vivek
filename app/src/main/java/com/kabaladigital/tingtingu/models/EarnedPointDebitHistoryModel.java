package com.kabaladigital.tingtingu.models;

public class EarnedPointDebitHistoryModel {
    private String id,mobileNumber,debitDate,debitPoints;

    public EarnedPointDebitHistoryModel(String id, String mobileNumber,
                                            String debitDate, String debitPoints) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.debitDate = debitDate;
        this.debitPoints = debitPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(String debitDate) {
        this.debitDate = debitDate;
    }

    public String getDebitPoints() {
        return debitPoints;
    }

    public void setDebitPoints(String debitPoints) {
        this.debitPoints = debitPoints;
    }
}
