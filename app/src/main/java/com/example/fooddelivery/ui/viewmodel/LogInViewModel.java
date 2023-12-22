package com.example.fooddelivery.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.fooddelivery.data.repo.FavoriteDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LogInViewModel extends ViewModel {
    public FavoriteDaoRepository favdao;

    @Inject
    public LogInViewModel(FavoriteDaoRepository favdao) {
        this.favdao = favdao;
    }

    public void showFavRv(){
        favdao.showFavorites();
    }
}
