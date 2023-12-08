package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.databinding.RecommendCardBinding;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.CardDesignHolding>{
    private List<Recommended> recommendedList;
    private Context context;

    public RecommendedAdapter(List<Recommended> recommendedList, Context context) {
        this.recommendedList = recommendedList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardDesignHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecommendCardBinding binding = RecommendCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CardDesignHolding(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolding holder, int position) {
        Recommended recommended = recommendedList.get(position);
        RecommendCardBinding r = holder.design;

        r.textViewPrice.setText(recommended.getFood_price());
        r.textViewFoodName.setText(recommended.getFood_name());
        r.textViiewScore.setText(recommended.getScore());
        r.textViewFoodType.setText(recommended.getFood_type());
    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class CardDesignHolding extends RecyclerView.ViewHolder{
      private RecommendCardBinding design;

        public CardDesignHolding( RecommendCardBinding design) {
            super(design.getRoot());
            this.design = design;
        }
    }
}
