package com.example.fooddelivery.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.CartFoods;

import com.example.fooddelivery.databinding.FragmentCartBinding;
import com.example.fooddelivery.ui.adapter.CartAdapter;

import com.example.fooddelivery.ui.viewmodel.CartViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private CartViewModel viewModelCart;


    public MutableLiveData<String> priceToMain = new MutableLiveData<>("0");

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater,container,false);

        CartFoods foods = new CartFoods();
        Log.e("Name:",foods.getYemek_adi() +"" );

        binding.buttonBackCart.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.backFromCartToMain);
        });

        //Cart RV
        binding.cartRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModelCart.cartFoods.observe(getViewLifecycleOwner(),cartFoods -> {
            CartAdapter adapter1 = new CartAdapter(cartFoods,requireContext(),viewModelCart);
            binding.cartRv.setAdapter(adapter1);
            int sum_price = cartFoods.stream().mapToInt(cartFoods1 ->
                    (cartFoods1.getYemek_fiyat() * cartFoods1.getYemek_siparis_adet())).sum();

            adapter1.isEmpty.observe(getViewLifecycleOwner(),isEmpty->{
                if(isEmpty){
                    binding.textViewTotal.setText( 0 + "₺" );
                }else{
                    binding.textViewTotal.setText( sum_price + "₺" );
                }
//                Log.e("Sonuç:",isEmpty+"");
            });


        });
        // ------------------

        //Send Price Data to MainPage


        //Nav and Status Bar Hide
        Window w = getActivity().getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);    //Hide Nav And Status Bar
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //--------------
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