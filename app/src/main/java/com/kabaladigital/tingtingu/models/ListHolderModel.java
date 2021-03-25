package com.kabaladigital.tingtingu.models;

import java.util.ArrayList;

public class ListHolderModel {
    static ArrayList list;

    public static ArrayList getList() {
        return list;
    }

    public static void setList(ArrayList list) {
        ListHolderModel.list = list;
    }
}
