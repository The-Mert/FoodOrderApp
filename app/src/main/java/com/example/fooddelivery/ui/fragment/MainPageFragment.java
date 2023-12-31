package com.example.fooddelivery.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;


import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.databinding.FragmentMainPageBinding;
import com.example.fooddelivery.ui.adapter.OrdersAdapter;
import com.example.fooddelivery.ui.adapter.RecommendedAdapter;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding binding;
    private MainPageViewModel viewModel;

    @SuppressLint({"CommitPrefEdits", "SetTextI18n"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater,container,false);

//        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("Amount", Context.MODE_PRIVATE);
//        String data_amount = sharedPreferences.getString("amount", "2 items in cart.");
//        String data_price = sharedPreferences.getString("price", "12₺");
//
//
//        binding.textViewCartAmount.setText(data_amount);
//        binding.buttonCartPage.setText(data_price);


        //Orders Now RV
        binding.orderRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.ordersList.observe(getViewLifecycleOwner(),ordersList -> {
            OrdersAdapter adapter1 = new OrdersAdapter(ordersList,requireContext(),viewModel);
            binding.orderRv.setAdapter(adapter1);

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

        });
        // ------------------





        binding.buttonCartPage.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathMainToCart);
        });

        binding.imageButtonLogOut.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathMainToProfile);
        });

        binding.imageButtonMainToFav.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathMainToFav);
        });


        Window w = getActivity().getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);    //Hide Nav And Status Bar
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainPageViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.showOrderNow();
        viewModel.showCartRv("mert_yazici");
//        viewModel.showRecommended();
    }
}