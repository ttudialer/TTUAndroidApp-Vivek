package com.kabaladigital.tingtingu.models;

public class ChooseMultiLangModel {

    private String Id, radioBtnName, radioBtnChangeName, radioBtnMode;

    public ChooseMultiLangModel(String id, String radioBtnName, String radioBtnChangeName, String radioBtnMode) {
        Id = id;
        this.radioBtnName = radioBtnName;
        this.radioBtnChangeName = radioBtnChangeName;
        this.radioBtnMode = radioBtnMode;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRadioBtnName() {
        return radioBtnName;
    }

    public void setRadioBtnName(String radioBtnName) {
        this.radioBtnName = radioBtnName;
    }

    public String getRadioBtnChangeName() {
        return radioBtnChangeName;
    }

    public void setRadioBtnChangeName(String radioBtnChangeName) {
        this.radioBtnChangeName = radioBtnChangeName;
    }

    public String getRadioBtnMode() {
        return radioBtnMode;
    }

    public void setRadioBtnMode(String radioBtnMode) {
        this.radioBtnMode = radioBtnMode;
    }
}
