package com.example.fooddelivery.data.entity;

public class Recommended {
    private int food_id;
    private int like_ammount;
    private String food_name;
    private String food_price;
    private String discount_amount;
    private String price_after;
    private String score;
    private String food_type;
    public Recommended() {
    }

    public Recommended(int food_id, int like_ammount, String food_name, String food_price, String discount_amount, String price_after, String score,String food_type) {
        this.food_id = food_id;
        this.like_ammount = like_ammount;
        this.food_name = food_name;
        this.food_price = food_price;
        this.discount_amount = discount_amount;
        this.price_after = price_after;
        this.score = score;
        this.food_type = food_type;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getLike_ammount() {
        return like_ammount;
    }

    public void setLike_ammount(int like_ammount) {
        this.like_ammount = like_ammount;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getPrice_after() {
        return price_after;
    }

    public void setPrice_after(String price_after) {
        this.price_after = price_after;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }
}
