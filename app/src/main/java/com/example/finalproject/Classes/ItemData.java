package com.example.finalproject.Classes;

import android.widget.Button;

public class ItemData {



    String name;
    String Des;
    Integer imagebb;
    Integer imageurl;

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public Integer getImagebb() {
        return imagebb;
    }

    public void setImagebb(Integer imagebb) {
        this.imagebb = imagebb;
    }

    public ItemData(String name, String des, Integer imagebb, Integer imageurl) {
        this.name = name;
        Des = des;
        this.imagebb = imagebb;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageurl() {
        return imageurl;
    }

    public void setImageurl(Integer imageurl) {
        this.imageurl = imageurl;
    }

}
