package com.example.fooddelivery.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.databinding.CartRvBinding;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.ui.viewmodel.CartViewModel;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartMenuCardHolding>{
    private List<CartFoods> cartFoodsList;

    private List<CartFoods> filterCartFoods;
    private Context context;
    public MutableLiveData<Boolean> isEmpty = new MutableLiveData<>(false);

    public MutableLiveData<String> itemAmount = new MutableLiveData<>("0");

    private CartViewModel viewModelCart;



    public CartAdapter(List<CartFoods> cartFoodsList, Context context, CartViewModel viewModelCart) {
        this.cartFoodsList = cartFoodsList;
        this.context = context;
        this.viewModelCart = viewModelCart;
        this.filterCartFoods = new ArrayList<>(cartFoodsList);
    }

    @NonNull
    @Override
    public CartMenuCardHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartRvBinding cartMenu = CartRvBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CartMenuCardHolding(cartMenu);
    }

    @Override
    public void onBindViewHolder(@NonNull CartMenuCardHolding holder, int position) {
        CartFoods cartFoods = cartFoodsList.get(position);
        CartRvBinding c = holder.bindingCart;
        List<String> controlList = new ArrayList<String>();
        controlList.add(cartFoods.getYemek_adi());
//        if (controlList.contains("Ayran")){
//            c.textViewPriceCartPage.setVisibility(View.GONE);
//        }else{
//
//        }
//        if (controlList.contains("Ayran")){}


        Log.e("positionss:", controlList.contains("Ayran")+" "+controlList+ " "+ cartFoods.getYemek_adi());


        //Image Resource
        String imageName = cartFoods.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + imageName;
        Glide.with(context).load(url).override(300,400).into(c.imageViewFoodCartPage);
        //-----------

        if(cartFoods.getYemek_adi().equals("Ayran") || cartFoods.getYemek_adi().equals("Fanta") ||cartFoods.getYemek_adi().equals("Su") ||cartFoods.getYemek_adi().equals("Kahve")){
            c.textViewFoodTypeCartPage.setText("Beverages");

        }else if (cartFoods.getYemek_adi().equals("Baklava") || cartFoods.getYemek_adi().equals("Kadayıf")|| cartFoods.getYemek_adi().equals("Sütlaç") || cartFoods.getYemek_adi().equals("Tiramisu")){
            c.textViewFoodTypeCartPage.setText("Dessert");
        }else{
            c.textViewFoodTypeCartPage.setText("Dinner");
        }


        c.textViewFoodNameCartPage.setText(cartFoods.getYemek_adi());  //Name

        c.textViewPriceCartPage.setText(cartFoods.getYemek_fiyat()+ "₺");  //Fiyat

        c.textViewAmountCartPage.setText(String.valueOf(cartFoods.getYemek_siparis_adet()));



        c.imageButtonDelete.setOnClickListener(v -> {
            Snackbar.make(v,cartFoods.getYemek_adi()+" sure to delete?",Snackbar.LENGTH_SHORT)
                    .setAction("YES",v1 -> {
                        viewModelCart.delete(cartFoods.getSepet_yemek_id(),"mert_yazici");
                        cartFoodsList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position,cartFoodsList.size());
                        if (cartFoodsList.size() == 0){
                            isEmpty.setValue(Boolean.TRUE);
                            Log.e("Sonuç:",isEmpty.getValue()+"");
                        }
                    })
                    .show();
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchFilter(String query) {
        cartFoodsList.clear();
        if (query.trim().isEmpty()) {
            cartFoodsList.addAll(filterCartFoods);
        } else {
            query = query.toLowerCase(new Locale("tr", "TR"));
            for (CartFoods cartFood : filterCartFoods) {
                if (normalizeString(cartFood.getYemek_adi()).contains(normalizeString(query))) {
                    cartFoodsList.add(cartFood);
                }
            }
        }
        notifyDataSetChanged();
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(new Locale("tr", "TR"));
    }
    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return cartFoodsList.size();
    }

    public class CartMenuCardHolding extends RecyclerView.ViewHolder{
        private CartRvBinding bindingCart;
        public CartMenuCardHolding(CartRvBinding bindingCart){
            super(bindingCart.getRoot());
            this.bindingCart = bindingCart;
        }


    }


}
