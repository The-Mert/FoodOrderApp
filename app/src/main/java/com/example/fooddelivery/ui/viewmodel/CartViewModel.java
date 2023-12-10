package com.example.fooddelivery.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.repo.FoodsDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CartViewModel  extends ViewModel {
    public FoodsDaoRepository fdao;
    public MutableLiveData<List<CartFoods>> cartFoods;
    @Inject
    public CartViewModel(FoodsDaoRepository fdao){
        this.fdao = fdao;
        showCartRv("mert_yazici");
        cartFoods = fdao.cartFoods;

    }

    public void showCartRv(String kullanici_adi){
        fdao.showCartRv(kullanici_adi);

    }

    public void delete(int sepet_yemek_id,String kullanici_adi){
        fdao.delete(sepet_yemek_id,kullanici_adi);
    }




}
