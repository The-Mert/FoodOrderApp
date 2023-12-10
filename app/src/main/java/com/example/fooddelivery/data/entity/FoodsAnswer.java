package com.example.fooddelivery.data.entity;

import java.util.List;

public class FoodsAnswer {
    private List<Foods> yemekler;
    private int success;

    public FoodsAnswer() {
    }

    public FoodsAnswer(List<Foods> yemekler, int success) {
        this.yemekler = yemekler;
        this.success = success;
    }

    public List<Foods> getYemekler() {
        return yemekler;
    }

    public void setYemekler(List<Foods> yemekler) {
        this.yemekler = yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
