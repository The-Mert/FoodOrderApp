package com.example.fooddelivery.retrofit;



import com.example.fooddelivery.data.entity.CRUDanswer;

import com.example.fooddelivery.data.entity.CartFoodsAnswer;

import com.example.fooddelivery.data.entity.FoodsAnswer;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface FoodsDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/ -> Base url
//    yemekler/tumYemekleriGetir.php -> API url
    @GET("yemekler/tumYemekleriGetir.php")
    Call<FoodsAnswer> showFoods();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDanswer> addCart(@Field("yemek_adi") String yemek_adi,
                             @Field("yemek_resim_adi") String yemek_resim_adi,
                             @Field("yemek_fiyat") int yemek_fiyat,
                             @Field("yemek_siparis_adet") int yemek_siparis_adet,
                             @Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<CartFoodsAnswer> showCart(@Field("kullanici_adi") String kullanici_adi);

}
