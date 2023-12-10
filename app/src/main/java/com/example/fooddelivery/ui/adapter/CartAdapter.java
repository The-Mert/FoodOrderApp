package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.ui.viewmodel.CartViewModel;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

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
        MainMenuCardBinding cartMenu = MainMenuCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CartMenuCardHolding(cartMenu);
    }

    @Override
    public void onBindViewHolder(@NonNull CartMenuCardHolding holder, int position) {
        CartFoods cartFoods = cartFoodsList.get(position);
        MainMenuCardBinding c = holder.bindingCart;



        if(cartFoods.getYemek_adi().equals("Ayran") || cartFoods.getYemek_adi().equals("Fanta") ||cartFoods.getYemek_adi().equals("Su") ||cartFoods.getYemek_adi().equals("Kahve")){
            c.textViewFoodTypeMainPage.setText("Beverages");
        }else if (cartFoods.getYemek_adi().equals("Baklava") || cartFoods.getYemek_adi().equals("Kadayıf")|| cartFoods.getYemek_adi().equals("Sütlaç") || cartFoods.getYemek_adi().equals("Tiramisu")){
            c.textViewFoodTypeMainPage.setText("Dessert");
        }else{
            c.textViewFoodTypeMainPage.setText("Dinner");
        }


        c.textViewFoodNameMainPage.setText(cartFoods.getYemek_adi());  //Name

        c.textViewPriceMainPage.setText(cartFoods.getYemek_fiyat()+ "₺");  //Fiyat

    }

    @Override
    public int getItemCount() {
        return cartFoodsList.size();
    }

    public class CartMenuCardHolding extends RecyclerView.ViewHolder{
        private MainMenuCardBinding bindingCart;
        public CartMenuCardHolding(MainMenuCardBinding bindingCart){
            super(bindingCart.getRoot());
            this.bindingCart = bindingCart;
        }


    }
}
