package com.example.finalproject.Classes;

public class CCClass {
    Integer imageurl;

    String des;



    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public CCClass(Integer imageurl, String des) {
        this.imageurl = imageurl;

        this.des = des;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }
}
