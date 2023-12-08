package com.example.fooddelivery.ui.fragment;


import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;


import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Recommended;
import com.example.fooddelivery.databinding.FragmentMainPageBinding;
import com.example.fooddelivery.ui.adapter.OrdersAdapter;
import com.example.fooddelivery.ui.adapter.RecommendedAdapter;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {
    private FragmentMainPageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainPageBinding.inflate(inflater,container,false);



//        binding.recommendedRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
//
//        ArrayList<Recommended> recommendedArrayList = new ArrayList<>();
//        Recommended r1 = new Recommended(1,72,"Ayran","80 ₺" ,"0", "0", "4,5", "Beverages");
//        Recommended r2 = new Recommended(2,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
//        Recommended r3 = new Recommended(3,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
//        Recommended r4 = new Recommended(4,22,"Fanta","10 ₺" ,"0", "0", "3,5", "Beverages");
//        recommendedArrayList.add(r1);
//        recommendedArrayList.add(r2);
//        recommendedArrayList.add(r3);
//        recommendedArrayList.add(r4);
//        RecommendedAdapter adapter = new RecommendedAdapter(recommendedArrayList,requireContext());
//        binding.recommendedRv.setAdapter(adapter);

//        binding.orderRv.setLayoutManager(new LinearLayoutManager(requireContext()));
//
//        ArrayList<Foods> foodsArrayList = new ArrayList<>();
//        Foods f1 = new Foods(1,"Ayran","8 ₺","Beverages","3");
//        Foods f2 = new Foods(2,"Fanta","","12 ₺","Beverages","3");
//        Foods f3 = new Foods(3,"Balık","4 ₺","Beverages","3");
//        Foods f4 = new Foods(4,"Et","5 ₺","Beverages","4");
//        foodsArrayList.add(f1);
//        foodsArrayList.add(f2);
//        foodsArrayList.add(f3);
//        foodsArrayList.add(f4);
//        OrdersAdapter adapter1 = new OrdersAdapter(foodsArrayList,requireContext());
//        binding.orderRv.setAdapter(adapter1);

        Window w = getActivity().getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return binding.getRoot();
    }



    public void search(String searchKey){
        Log.e("S","asda");
    }
}