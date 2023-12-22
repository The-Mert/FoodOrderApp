package com.example.fooddelivery.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.data.repo.FavoriteDaoRepository;
import com.example.fooddelivery.data.repo.FoodsDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainPageViewModel extends ViewModel {
    public FoodsDaoRepository fdao;
    public FavoriteDaoRepository favdao;
    public MutableLiveData<List<Foods>> ordersList;
    public MutableLiveData<List<Recommended>> recommendedList;

    @Inject
    public MainPageViewModel(FoodsDaoRepository fdao,FavoriteDaoRepository favdao){
        this.fdao = fdao;
        this.favdao = favdao;
//        showRecommended();
        showOrderNow();
        ordersList = fdao.ordersList;
        recommendedList = fdao.recommendedList;
    }


//    public void showRecommended(){
//        fdao.showRecommended();
//    }

    public void showOrderNow(){
        fdao.showOrderNow();
    }
    public void addCart(String yemek_adi,String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet,String kullanici_adi){
        fdao.addCart(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }

    public void addFav(String email,String username,String password,String food_name,String img_name,String food_amount,String owner_name){
        favdao.saveFavorite(email,username,password,food_name,img_name,food_amount,owner_name);
    }

    public void showCartRv(String kullanici_adi){
        fdao.showCartRv(kullanici_adi);

    }



}
