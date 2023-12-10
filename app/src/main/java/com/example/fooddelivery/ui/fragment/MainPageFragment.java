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
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.databinding.FragmentMainPageBinding;
import com.example.fooddelivery.ui.adapter.OrdersAdapter;
import com.example.fooddelivery.ui.adapter.RecommendedAdapter;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding binding;
    private MainPageViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater,container,false);




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
        });
        // ------------------

        binding.buttonCartPage.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathMainToCart);
        });

//        binding.imageButtonLogOut.setOnClickListener(v -> {
//            Foods foods = new Foods();
//            String yemek_adi = foods.getYemek_adi();
//            String yemek_resim_adi = foods.getYemek_resim_adi();
//            int yemek_fiyat = foods.getYemek_fiyat();
////            String yemek_siparis_str = m.textViewAmountMainPage.getText().toString();
//            int yemek_siparis_adet = 1;
//            String kullanici_adi = "merty";
//            viewModel.addCart("Ayran","ayran.png",3,1,"mert_yazici");
//        });


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