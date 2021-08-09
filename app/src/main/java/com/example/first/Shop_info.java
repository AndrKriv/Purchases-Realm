package com.example.first;

import io.realm.RealmObject;

public class Shop_info extends RealmObject{
    private String date;//дата
    private String place;//место покупки
    private String price;//стоимость покупки

    public Shop_info(){

    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}