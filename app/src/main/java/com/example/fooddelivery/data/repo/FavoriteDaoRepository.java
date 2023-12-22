package com.example.fooddelivery.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.room.FavoritesDao;

import java.util.List;

import io.reactivex.CompletableObserver;
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
                        Log.e("Favorit:",logins+"");
                        favoritesList.setValue(logins);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void saveFavorite(String email,String username,String password,String food_name,String img_name,String food_amount,String owner_name){
        Login newSave = new Login(0,email,username,password,food_name,img_name,food_amount,owner_name);
        favoritesDao.save(newSave).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void delteFavorite(int person_id){
        Login newSave = new Login(person_id,"","","","","","","");
        favoritesDao.delete(newSave).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onComplete() {
                        showFavorites();
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public  void controlUsername(String username){

    }
}
