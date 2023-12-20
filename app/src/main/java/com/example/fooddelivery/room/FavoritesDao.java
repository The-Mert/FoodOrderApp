package com.example.fooddelivery.room;

import androidx.room.Dao;
import androidx.room.Query;


import com.example.fooddelivery.data.entity.Login;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FavoritesDao {
    @Query("SELECT * FROM log")
    Single<List<Login>> loadLogin();
}
