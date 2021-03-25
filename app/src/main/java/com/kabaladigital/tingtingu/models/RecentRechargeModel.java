package com.kabaladigital.tingtingu.models;

public class RecentRechargeModel {
    String id,nameOrNumber,phoneNumber,date,prices;
    int image;

    public RecentRechargeModel(String id, String nameOrNumber, String phoneNumber,
                                  String date, String prices, int image) {
        this.id = id;
        this.nameOrNumber = nameOrNumber;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.prices = prices;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOrNumber() {
        return nameOrNumber;
    }

    public void setNameOrNumber(String nameOrNumber) {
        this.nameOrNumber = nameOrNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
