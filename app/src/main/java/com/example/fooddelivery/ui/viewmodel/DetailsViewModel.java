package com.example.fooddelivery.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.data.repo.FoodsDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailsViewModel extends ViewModel {

    public FoodsDaoRepository fdao;

    @Inject
    public DetailsViewModel(FoodsDaoRepository fdao){
        this.fdao = fdao;
//        showCartRv("mert_yazici");
    }

    public void showCartRv(String kullanici_adi){
        fdao.showCartRv(kullanici_adi);

    }

    public void addCart(String yemek_adi,String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet,String kullanici_adi){
        fdao.addCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }
}
