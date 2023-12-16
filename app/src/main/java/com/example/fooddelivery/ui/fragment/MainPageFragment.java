package com.example.fooddelivery.ui.fragment;


import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.databinding.FragmentMainPageBinding;
import com.example.fooddelivery.ui.adapter.CartAdapter;
import com.example.fooddelivery.ui.adapter.OrdersAdapter;
import com.example.fooddelivery.ui.adapter.RecommendedAdapter;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding binding;
    private MainPageViewModel viewModel;
    private int amount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater,container,false);

        CartFragment fragment = new CartFragment();
        String total = fragment.priceToMain.getValue();
        Log.e("totalPrice:", total);


        //Recommended RV
        binding.recommendedRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        viewModel.recommendedList.observe(getViewLifecycleOwner(),recommendedList -> {
            RecommendedAdapter adapter = new RecommendedAdapter(recommendedList,requireContext(),viewModel);
            binding.recommendedRv.setAdapter(adapter);



        });
        //-------------------

        //Orders Now RV
        binding.orderRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.ordersList.observe(getViewLifecycleOwner(),ordersList -> {
            OrdersAdapter adapter1 = new OrdersAdapter(ordersList,requireContext(),viewModel);
            binding.orderRv.setAdapter(adapter1);
            int sum_price = 0;
            adapter1.amount.observe(getViewLifecycleOwner(),s->{
                this.amount = s;
            });
            for (Foods orderFoods : ordersList) {
                sum_price += (orderFoods.getYemek_fiyat() * this.amount);
            }
            binding.buttonCartPage.setText(String.valueOf(sum_price));
        });
        // ------------------


        binding.buttonCartPage.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathMainToCart);
        });

        binding.imageButtonLogOut.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathFromMainToLogin);
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
//        viewModel.showRecommended();
    }
}