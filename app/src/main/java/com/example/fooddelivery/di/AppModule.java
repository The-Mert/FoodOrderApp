package com.example.fooddelivery.di;

import android.content.Context;

import androidx.room.Room;

import com.example.fooddelivery.data.repo.FoodsDaoRepository;
import com.example.fooddelivery.retrofit.ApiUtils;
import com.example.fooddelivery.retrofit.FoodsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public FoodsDaoRepository provideKisilerDaoRepository(FoodsDao fdao){
        return new FoodsDaoRepository(fdao);
    }

    @Provides
    @Singleton
    public FoodsDao provideFoodsDao(@ApplicationContext Context context){
        return ApiUtils.getFoodsDao();
    }

}
