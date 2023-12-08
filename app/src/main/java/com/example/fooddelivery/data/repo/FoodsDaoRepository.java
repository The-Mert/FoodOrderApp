package com.example.fooddelivery.data.repo;

import android.util.Log;
import android.widget.SearchView;

import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.FoodsAnswer;
import com.example.fooddelivery.retrofit.FoodsDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsDaoRepository {
    private FoodsDao fdao;

    public FoodsDaoRepository(FoodsDao fdao){
        this.fdao = fdao;
    }

    public void showFoods(){
        fdao.showFoods().enqueue(new Callback<FoodsAnswer>() {
            @Override
            public void onResponse(Call<FoodsAnswer> call, Response<FoodsAnswer> response) {
                List<Foods> foodsList = response.body().getFoods();

            }

            @Override
            public void onFailure(Call<FoodsAnswer> call, Throwable t) {

            }
        });
    }
    public void addCart(String food_name){
        Log.e("s","a");
    }

//    public void search(String searchKey){
//        binding.mainPageSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {  // Works with search icon on keyboard
//                search(query);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) { // Works while typing or deleting
//                search(newText);
//                return true;
//            }
//        });
//    }
}
