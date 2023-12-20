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

//    public void addFav(){
//
//    }
}
