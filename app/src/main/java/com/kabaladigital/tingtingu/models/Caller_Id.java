package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Caller_Id {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Employee_Data")
    @Expose
    private EmployeeData employeeData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeeData getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(EmployeeData employeeData) {
        this.employeeData = employeeData;
    }

    public class EmployeeData {

        @SerializedName("Client_Name")
        @Expose
        private String clientName;
        @SerializedName("Client_Display_Name")
        @Expose
        private String clientDisplayName;
        @SerializedName("Employee_Name")
        @Expose
        private String employeeName;
        @SerializedName("Thumbnail_Photo_url")
        @Expose
        private String thumbnailPhotoUrl;
        @SerializedName("Main_Photo_url")
        @Expose
        private String mainPhotoUrl;

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getClientDisplayName() {
            return clientDisplayName;
        }

        public void setClientDisplayName(String clientDisplayName) {
            this.clientDisplayName = clientDisplayName;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getThumbnailPhotoUrl() {
            return thumbnailPhotoUrl;
        }

        public void setThumbnailPhotoUrl(String thumbnailPhotoUrl) {
            this.thumbnailPhotoUrl = thumbnailPhotoUrl;
        }

        public String getMainPhotoUrl() {
            return mainPhotoUrl;
        }

        public void setMainPhotoUrl(String mainPhotoUrl) {
            this.mainPhotoUrl = mainPhotoUrl;
        }

    }
}
