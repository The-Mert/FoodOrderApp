package com.example.fooddelivery.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.data.repo.FavoriteDaoRepository;
import com.example.fooddelivery.data.repo.FoodsDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FavoriteViewModel extends ViewModel {
    public FavoriteDaoRepository favdao;
    public FoodsDaoRepository foodsDao;
    public MutableLiveData<List<Login>> favoritesList;

    @Inject
    public FavoriteViewModel(FavoriteDaoRepository favdao,FoodsDaoRepository foodsDao){
        this.favdao = favdao;
        this.foodsDao = foodsDao;
        this.favoritesList = favdao.favoritesList;
        showFavRv();
    }

    public void showFavRv(){
        favdao.showFavorites();
    }

    public void addFav(String email,String username,String password,String food_name,String img_name,String food_amount,String owner_name){
        favdao.saveFavorite(email,username,password,food_name,img_name,food_amount,owner_name);
    }

    public void deleteFav(int person_id){
        favdao.delteFavorite(person_id);
    }
}
