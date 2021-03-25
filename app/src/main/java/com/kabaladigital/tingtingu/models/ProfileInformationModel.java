package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileInformationModel {

    @SerializedName("knownLanguages")
    @Expose
    private List<String> knownLanguages = null;
    @SerializedName("haveProfile")
    @Expose
    private Boolean haveProfile;
    @SerializedName("creditPoint")
    @Expose
    private List<String> creditPoint = null;
    @SerializedName("mobileInfo")
    @Expose
    private MobileInfo mobileInfo;
    @SerializedName("membership")
    @Expose
    private Integer membership;
    @SerializedName("refer")
    @Expose
    private Refer refer;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("pincode")
    @Expose
    private Integer pincode;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("address")
    @Expose
    private Address address;

    public List<String> getKnownLanguages() {
        return knownLanguages;
    }

    public void setKnownLanguages(List<String> knownLanguages) {
        this.knownLanguages = knownLanguages;
    }

    public Boolean getHaveProfile() {
        return haveProfile;
    }

    public void setHaveProfile(Boolean haveProfile) {
        this.haveProfile = haveProfile;
    }

    public List<String> getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(List<String> creditPoint) {
        this.creditPoint = creditPoint;
    }

    public MobileInfo getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

    public Integer getMembership() {
        return membership;
    }

    public void setMembership(Integer membership) {
        this.membership = membership;
    }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public class Refer {

        @SerializedName("referId")
        @Expose
        private String referId;

        public String getReferId() {
            return referId;
        }

        public void setReferId(String referId) {
            this.referId = referId;
        }

    }

    public class MobileInfo {

        @SerializedName("mobileNumber")
        @Expose
        private long mobileNumber;
        @SerializedName("numberType")
        @Expose
        private Integer numberType;
        @SerializedName("operator")
        @Expose
        private String operator;
        @SerializedName("operatorCircle")
        @Expose
        private String operatorCircle;
        @SerializedName("operatorType")
        @Expose
        private String operatorType;

        public long getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(long mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public Integer getNumberType() {
            return numberType;
        }

        public void setNumberType(Integer numberType) {
            this.numberType = numberType;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getOperatorCircle() {
            return operatorCircle;
        }

        public void setOperatorCircle(String operatorCircle) {
            this.operatorCircle = operatorCircle;
        }

        public String getOperatorType() {
            return operatorType;
        }

        public void setOperatorType(String operatorType) {
            this.operatorType = operatorType;
        }

    }

    public class Address {

        @SerializedName("address1")
        @Expose
        private String address1;
        @SerializedName("address2")
        @Expose
        private String address2;
        @SerializedName("address3")
        @Expose
        private String address3;

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

    }
}
