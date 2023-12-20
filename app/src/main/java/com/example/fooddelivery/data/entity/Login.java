package com.example.fooddelivery.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "log")
public class Login {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    @NonNull
    private int person_id;
    @ColumnInfo(name = "email")
    @NonNull
    private String email;
    @ColumnInfo(name = "username")
    @NonNull
    private String username;
    @ColumnInfo(name = "password")
    @NonNull
    private String password;
    @ColumnInfo(name = "food_name")
    @NonNull
    private String food_name;
    @ColumnInfo(name = "img_name")
    @NonNull
    private String img_name;
    @ColumnInfo(name = "food_price")
    @NonNull
    private String food_amount;
    @ColumnInfo(name = "owner_name")
    @NonNull
    private String owner_name;

    public Login() {
    }

    public Login(int person_id, String email, String username, String password, String food_name, String img_name, String food_amount, String owner_name) {
        this.person_id = person_id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.food_name = food_name;
        this.img_name = img_name;
        this.food_amount = food_amount;
        this.owner_name = owner_name;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public String getFood_amount() {
        return food_amount;
    }

    public void setFood_amount(String  food_amount) {
        this.food_amount = food_amount;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
}