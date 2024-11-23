package com.example.hostel_magement.ui;

public class DataClass_Owner_rooms {
    private String dataroomtype;
    private String datafloor_r;
    private String dataroom_r;
    private  String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataRoomType(){
        return dataroomtype;

    }
    public String getDatafloor_r(){
        return datafloor_r;

    }
    public String getDataroom_r(){
        return dataroom_r;

    }


    public  DataClass_Owner_rooms(String dataroomtype,String datafloor_r,String dataroom_r){
        this.dataroomtype =dataroomtype;
        this.datafloor_r =datafloor_r;
        this.dataroom_r =dataroom_r;
    }


    public DataClass_Owner_rooms() {
        // Default constructor with no arguments
    }

}
