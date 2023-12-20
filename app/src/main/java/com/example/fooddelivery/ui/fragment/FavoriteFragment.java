package com.example.fooddelivery.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentFavoriteBinding;
import com.example.fooddelivery.ui.adapter.FavoritesAdapter;
import com.example.fooddelivery.ui.viewmodel.FavoriteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteFragment extends Fragment {

   private FragmentFavoriteBinding binding;

   private FavoriteViewModel favoriteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false);

        //Fav Rv
        binding.favRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        favoriteViewModel.favoritesList.observe(getViewLifecycleOwner(),favList ->{
            FavoritesAdapter adapter = new FavoritesAdapter(favList,requireContext(),favoriteViewModel);
            binding.favRv.setAdapter(adapter);
            Log.e("veritabancerik:",favList.size() + "");

        });
        //---------------------

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
    }
}