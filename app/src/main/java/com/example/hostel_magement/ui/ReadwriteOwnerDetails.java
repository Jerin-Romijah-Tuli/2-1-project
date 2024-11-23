package com.example.hostel_magement.ui;

public class ReadwriteOwnerDetails {
    private String hostelname,mobile,facility,safety,meal,location;



    private  String key;

    public String getKey() {
        return key;
    }
    public void setkey(String key) {
        this.key =key;
    }



    public String getHostelname() {
        return hostelname;
    }

    public String getFacility() {
        return facility;
    }

    public String getSafety() {
        return safety;
    }

    public String getLocation() {
        return location;
    }

    public String getMeal() {
        return meal;
    }

    public String getMobile() {
        return mobile;
    }

    public ReadwriteOwnerDetails(String textHostelName, String textMobile, String textFacility, String textSafety, String textMeal,
                                 String textLocation) {
        this.hostelname =textHostelName;
        this.mobile = textMobile;
        this.facility = textFacility;
        this.safety = textSafety;
        this.meal = textMeal;
        this.location =textLocation;

    }

    public ReadwriteOwnerDetails() {

    }


}
