package com.example.fooddelivery.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.data.entity.CRUDanswer;
import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.entity.CartFoodsAnswer;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.FoodsAnswer;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.retrofit.FoodsDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsDaoRepository {
    public MutableLiveData<List<Foods>> ordersList = new MutableLiveData<>();
    public MutableLiveData<List<Recommended>> recommendedList = new MutableLiveData<>();

    public MutableLiveData<List<CartFoods>> cartFoods = new MutableLiveData<>();

    private FoodsDao fdao;

    public FoodsDaoRepository(FoodsDao fdao){
        this.fdao = fdao;
    }

    public void delete(int food_id){
        Log.e("Silinen","a");
    }
    public void search(String searchKey){
        Log.e("Ara","asda");
    }

    public void addCart(String yemek_adi,String yemek_resim_adi,int yemek_fiyat,int yemek_siparis_adet,String kullanici_adi){
        fdao.addCart(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(new Callback<CRUDanswer>() {
            @Override
            public void onResponse(Call<CRUDanswer> call, Response<CRUDanswer> response) {
//                    response.body(); // GetMessage or GetSuccess
//                showCartRv("mert_yazici");
            }
            @Override
            public void onFailure(Call<CRUDanswer> call, Throwable t) {
                Log.e("Kontroll: ","İşlem Başarısız");
            }
        });
    }

    public void update(int food_id,String food_name,String food_price){
        Log.e("Güncelle","a");
    }

    public void showRecommended(){
        ArrayList<Recommended> list = new ArrayList<>();
        Recommended r1 = new Recommended(1,72,"Ayran","80 ₺" ,"0", "0", "4,5", "Beverages");
        Recommended r2 = new Recommended(2,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
        Recommended r3 = new Recommended(3,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
        Recommended r4 = new Recommended(4,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);

        recommendedList.setValue(list);
    }

    public void showOrderNow(){
        fdao.showFoods().enqueue(new Callback<FoodsAnswer>() {
            @Override
            public void onResponse(Call<FoodsAnswer> call, Response<FoodsAnswer> response) {
                List<Foods> foodsList = response.body().getYemekler();
                ordersList.setValue(foodsList);
            }

            @Override
            public void onFailure(Call<FoodsAnswer> call, Throwable t) {

            }
        });
    }

    public void showCartRv(String kullanici_adi){
        fdao.showCart(kullanici_adi).enqueue(new Callback<CartFoodsAnswer>() {
            @Override
            public void onResponse(Call<CartFoodsAnswer> call, Response<CartFoodsAnswer> response) {
                List<CartFoods> cartFoodsList = response.body().getSepet_yemekler();   // Sorun buradaaaaaaaaaaaaaaaaaa   cartFoodsList null dönüyor sebebi ne acaba
                cartFoods.setValue(cartFoodsList);
                CartFoods foods = new CartFoods();

            }

            @Override
            public void onFailure(Call<CartFoodsAnswer> call, Throwable t) {

            }
        });
    }

    public void delete(int sepet_yemek_id,String kullanici_adi){
        fdao.delete(sepet_yemek_id,kullanici_adi).enqueue(new Callback<CRUDanswer>() {
            @Override
            public void onResponse(Call<CRUDanswer> call, Response<CRUDanswer> response) {
                showCartRv("mert_yazici");
            }

            @Override
            public void onFailure(Call<CRUDanswer> call, Throwable t) {

            }
        });
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
