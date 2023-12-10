package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.databinding.CartRvBinding;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.ui.viewmodel.CartViewModel;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartMenuCardHolding>{
    private List<CartFoods> cartFoodsList;
    private Context context;

    private CartViewModel viewModelCart;

    public CartAdapter(List<CartFoods> cartFoodsList, Context context,CartViewModel viewModelCart) {
        this.cartFoodsList = cartFoodsList;
        this.context = context;
        this.viewModelCart = viewModelCart;
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

        c.imageButtonDelete.setOnClickListener(v -> {
            Snackbar.make(v,cartFoods.getYemek_adi()+" sure to delete?",Snackbar.LENGTH_SHORT)
                    .setAction("YES",v1 -> {
                        viewModelCart.delete(cartFoods.getSepet_yemek_id(),"mert_yazici");
                    })
                    .show();
        });

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
