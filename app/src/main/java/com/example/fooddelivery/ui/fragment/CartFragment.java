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
import android.widget.SearchView;


import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.CartFoods;

import com.example.fooddelivery.databinding.FragmentCartBinding;
import com.example.fooddelivery.ui.adapter.CartAdapter;

import com.example.fooddelivery.ui.viewmodel.CartViewModel;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

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
            AtomicInteger sum_price = new AtomicInteger(cartFoods.stream().mapToInt(cartFoods1 ->
                    (cartFoods1.getYemek_fiyat() * cartFoods1.getYemek_siparis_adet())).sum());

            binding.deleteAll.setOnClickListener(s->{
                while(cartFoods.size()>0){
                for(CartFoods cartFoods1 : cartFoods){
                    viewModelCart.delete(cartFoods1.getSepet_yemek_id(),"mert_yazici");
                }
                cartFoods.clear();
                Log.e("SizeFood:",cartFoods.size() + "");
                }
                adapter1.notifyDataSetChanged();
                adapter1.isEmpty.observe(getViewLifecycleOwner(),isEmpty->{
                    if(isEmpty || cartFoods.size()==0){
                        binding.textViewDeliveryPrice.setText("");
                        binding.textViewDeliveryText.setText("");
                        binding.textViewTotal.setText( 0 + "₺" );
                    }else{
                        sum_price.addAndGet(10);
                        binding.textViewDeliveryPrice.setText("10₺");
                        binding.textViewDeliveryText.setText("Delivery Fee");
                        binding.textViewTotal.setText( sum_price.get() + "₺" );
                    }
                });
            });
            binding.mainPageSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter1.searchFilter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter1.searchFilter(newText);
                    return false;
                }
            });

            adapter1.isEmpty.observe(getViewLifecycleOwner(),isEmpty->{
                if(isEmpty || cartFoods.size()==0){
                    binding.textViewDeliveryPrice.setText("");
                    binding.textViewDeliveryText.setText("");
                    binding.textViewTotal.setText( 0 + "₺" );
                }else{
                    sum_price.addAndGet(10);
                    binding.textViewDeliveryPrice.setText("10₺");
                    binding.textViewDeliveryText.setText("Delivery Fee");
                    binding.textViewTotal.setText( sum_price.get() + "₺" );
                }
            });


        });
        // ------------------



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