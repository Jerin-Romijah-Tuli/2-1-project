package com.example.hostel_magement.ui;

public class DataClass_Owner {


    private String dataname;
    private String datafloor;
    private String dataroom;
    private String datainstitute;
    private String dataaddress;
    private String datamobile;
    private  String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataname(){
        return dataname;

    }
    public String getDatafloor(){
        return datafloor;

    }
    public String getDataroom(){
        return dataroom;

    }
    public String getDatainstitute(){
        return datainstitute;

    }
    public String getDataaddress(){
        return dataaddress;

    }
    public String getDatamobile(){
        return datamobile;

    }


    public DataClass_Owner(String dataname,String datafloor,String dataroom,String datainstitute,String dataaddress,String datamobile){
        this.dataname =dataname;
        this.datafloor =datafloor;
        this.dataroom =dataroom;
        this.datainstitute =datainstitute;
        this.dataaddress =dataaddress;
        this.datamobile =datamobile;
    }

    public DataClass_Owner(){

    }


}
