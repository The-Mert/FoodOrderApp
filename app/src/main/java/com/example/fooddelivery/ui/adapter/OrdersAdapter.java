package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.databinding.RecommendCardBinding;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MainMenuCardHolding> {
    private List<Foods> foodsList;
    private Context context;

    public OrdersAdapter(List<Foods> foodsList, Context context) {
        this.foodsList = foodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MainMenuCardHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainMenuCardBinding mainMenu = MainMenuCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MainMenuCardHolding(mainMenu);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuCardHolding holder, int position) {
        Foods foods = foodsList.get(position);
        MainMenuCardBinding m = holder.bindingMenu;

        m.textViewAmountMainPage.setText(foods.getCart_amount());
        m.textViewFoodNameMainPage.setText(foods.getFood_name());
        m.textViewFoodTypeMainPage.setText(foods.getFood_type());
        m.textViewPriceMainPage.setText(foods.getFood_price());

    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    public class MainMenuCardHolding extends RecyclerView.ViewHolder{
        private MainMenuCardBinding bindingMenu;

        public MainMenuCardHolding(MainMenuCardBinding bindingMenu) {
            super(bindingMenu.getRoot());
            this.bindingMenu = bindingMenu;
        }
    }
}
