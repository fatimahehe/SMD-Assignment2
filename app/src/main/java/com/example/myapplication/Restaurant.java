package com.example.myapplication;
import java.io.Serializable;

public class Restaurant implements Serializable {
    private String itemName;
    private String subItemName;
    private String location;
    private String phone;
    private String description;
    private int rating;

    public Restaurant(String itemName, String subItemName,String location, String phone, String description, int rating) {
        this.itemName = itemName;
        this.subItemName = subItemName;
        this.location=location;
        this.phone = phone;
        this.description = description;
        this.rating=rating;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSubItemName() {
        return subItemName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }
    public int getRating(){
        return rating;
    }
}
