package com.example.fooddelivery.data.entity;

import java.io.Serializable;

public class Foods {
    private int food_id;
    private String food_name;
    private String food_img;
    private String food_price;

    private String food_type;

    private String cart_amount;
    public Foods() {
    }

    public Foods(int food_id, String food_name,String food_img, String food_price, String food_type, String cart_amount) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_type = food_type;
        this.cart_amount = cart_amount;
        this.food_img = food_img;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getCart_amount() {
        return cart_amount;
    }

    public void setCart_amount(String cart_amount) {
        this.cart_amount = cart_amount;
    }
}
