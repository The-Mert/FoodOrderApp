package com.example.fooddelivery.retrofit;

import com.example.fooddelivery.data.entity.FoodsAnswer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodsDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/ -> Base url
    //yemekler/tumYemekleriGetir.php -> API url
    @GET("yemekler/tumYemekleriGetir.php")
    Call<FoodsAnswer> showFoods();
}
