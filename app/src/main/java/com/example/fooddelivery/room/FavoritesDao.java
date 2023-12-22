package com.example.fooddelivery.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.fooddelivery.data.entity.Login;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface FavoritesDao {
    @Query("SELECT * FROM log")
    Single<List<Login>> loadLogin();

    @Insert
    Completable save(Login log);

    @Update
    Completable update(Login logUpdate);

    @Delete
    Completable delete(Login logDelete);
}
