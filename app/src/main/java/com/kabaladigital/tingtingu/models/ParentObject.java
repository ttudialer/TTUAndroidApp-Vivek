package com.kabaladigital.tingtingu.models;

import java.util.ArrayList;

public class ParentObject {

    public String date;
    public String point;
    private ArrayList<ChildObject> children;

    public ParentObject() {
    }

    public ArrayList<ChildObject> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildObject> children) {
        this.children = children;
    }

    public ParentObject(String date, String point) {
        this.date = date;
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
