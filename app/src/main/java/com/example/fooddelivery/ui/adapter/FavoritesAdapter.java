package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.databinding.FavoriteCartBinding;
import com.example.fooddelivery.databinding.FragmentFavoriteBinding;
import com.example.fooddelivery.ui.fragment.FavoriteFragmentDirections;
import com.example.fooddelivery.ui.fragment.MainPageFragmentDirections;
import com.example.fooddelivery.ui.viewmodel.FavoriteViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesCardHolding>{
    private List<Login> loginList;
    private final Context context;
    private FavoriteViewModel favoriteViewModel;
    public MutableLiveData<Boolean> pressed;

    public FavoritesAdapter(List<Login> loginList, Context context, FavoriteViewModel favoriteViewModel) {
        this.loginList = loginList;
        this.context = context;
        this.favoriteViewModel = favoriteViewModel;
    }

    @NonNull
    @Override
    public FavoritesCardHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoriteCartBinding favMenu = FavoriteCartBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FavoritesCardHolding(favMenu);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesCardHolding holder, int position) {
        Login login_position = loginList.get(position);
        FavoriteCartBinding f = holder.favBinding;

        //Image Resource
        String imageName = login_position.getImg_name();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + imageName;
        Glide.with(context).load(url).override(300,400).into(f.imageViewFoodFav);
        //-----------

        f.textViewFoodNameFav.setText(login_position.getFood_name());
        f.textViewPriceFav.setText(login_position.getFood_amount());
        Log.e("veritabanıiçerik:",login_position.getFood_name()+" "+login_position.getFood_amount());

        f.imageButtonDeleteFav.setOnClickListener(v->{
            Snackbar.make(v,"Do you really want to delete "+login_position.getFood_name()+" from favorites?",Snackbar.LENGTH_SHORT)
                    .setAction("YES",v1 -> {
                        favoriteViewModel.deleteFav(login_position.getPerson_id());
                        Snackbar.make(v1,login_position.getFood_name()+" has been deleted from favorites.", Snackbar.LENGTH_SHORT).show();
                    })
                    .show();
        });

        f.imageViewFoodFav.setOnClickListener(v -> {
            Foods food = new Foods();
            FavoriteFragmentDirections.PathFavToDetail pathFavToDetail = FavoriteFragmentDirections.pathFavToDetail(food,login_position);
            Navigation.findNavController(v).navigate(pathFavToDetail);
        });

//        m.imageViewFoodMainPage.setOnClickListener(v -> {       // Path to Details Fragment
//            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods);
//            Navigation.findNavController(v).navigate(pathDetails);
//
//        });
//        m.textViewFoodNameMainPage.setOnClickListener(v -> {       // Path to Details Fragment
//            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods);
//            Navigation.findNavController(v).navigate(pathDetails);
//
//        });
//        m.textViewFoodTypeMainPage.setOnClickListener(v -> {       // Path to Details Fragment
//            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods);
//            Navigation.findNavController(v).navigate(pathDetails);
//
//        });
//        m.textViewPriceMainPage.setOnClickListener(v -> {       // Path to Details Fragment
//            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods);
//            Navigation.findNavController(v).navigate(pathDetails);
//
//        });
    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    public class FavoritesCardHolding extends RecyclerView.ViewHolder{
        private FavoriteCartBinding favBinding;

        public FavoritesCardHolding( FavoriteCartBinding favBinding) {
            super(favBinding.getRoot());
            this.favBinding = favBinding;
        }
    }
}
