package com.kabaladigital.tingtingu.models;

public class ChildObject {
    public String childPoint;
    public String childDate;
    public int childType;
    public String childDesc;
    public String childMobileNumber;
    public String surveyId;

    public ChildObject(String childPoint) {
        this.childPoint = childPoint;
    }

    public ChildObject() {

    }

    public void setChildPoint(String childPoint) {
        this.childPoint = childPoint;
    }

    public void setChildDate(String childDate) {
        this.childDate = childDate;
    }

    public String getChildPoint() {
        return childPoint;
    }

    public String getChildDate() {
        return childDate;
    }

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public String getChildDesc() {
        return childDesc;
    }

    public void setChildDesc(String childDesc) {
        this.childDesc = childDesc;
    }

    public String getChildMobileNumber() {
        return childMobileNumber;
    }

    public void setChildMobileNumber(String childMobileNumber) {
        this.childMobileNumber = childMobileNumber;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }
}
