package com.example.fooddelivery.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fooddelivery.data.entity.Login;

@Database(entities = {Login.class},version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract FavoritesDao getFavoriteDao();
}
