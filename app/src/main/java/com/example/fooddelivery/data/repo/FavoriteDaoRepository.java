package com.example.fooddelivery.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.room.FavoritesDao;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavoriteDaoRepository {
    public MutableLiveData<List<Login>> favoritesList = new MutableLiveData<>();

    private FavoritesDao favoritesDao;

    public FavoriteDaoRepository(FavoritesDao favoritesDao){
        this.favoritesDao = favoritesDao;
    }

    public void showFavorites(){
        favoritesDao.loadLogin().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Login>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onSuccess(List<Login> logins) {
                        favoritesList.setValue(logins);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
