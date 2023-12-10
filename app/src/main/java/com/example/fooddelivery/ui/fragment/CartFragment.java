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
import android.view.Window;
import android.view.WindowManager;


import com.example.fooddelivery.data.entity.CartFoods;

import com.example.fooddelivery.databinding.FragmentCartBinding;
import com.example.fooddelivery.ui.adapter.CartAdapter;

import com.example.fooddelivery.ui.viewmodel.CartViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private CartViewModel viewModelCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater,container,false);

        CartFoods foods = new CartFoods();
        Log.e("Name:",foods.getYemek_adi() +"" );

        //Cart RV
        binding.cartRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModelCart.cartFoods.observe(getViewLifecycleOwner(),cartFoods -> {
            CartAdapter adapter1 = new CartAdapter(cartFoods,requireContext(),viewModelCart);
            binding.cartRv.setAdapter(adapter1);
        });
        // ------------------


        //Add Cart API With Cart Button


        //  ----------


        Window w = getActivity().getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);    //Hide Nav And Status Bar
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelCart = new ViewModelProvider(this).get(CartViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModelCart.showCartRv("mert_yazici");


    }
}