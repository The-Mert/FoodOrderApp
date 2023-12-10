package com.example.fooddelivery.data.entity;

import java.util.List;

public class CartFoodsAnswer {
    private List<CartFoods> sepet_yemekler;

    private int success;

    public CartFoodsAnswer() {
    }

    public CartFoodsAnswer(List<CartFoods> sepet_yemekler, int success) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
    }

    public List<CartFoods> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<CartFoods> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
