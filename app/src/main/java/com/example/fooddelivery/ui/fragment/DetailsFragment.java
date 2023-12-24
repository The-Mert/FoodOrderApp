
package com.example.fooddelivery.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.databinding.FragmentDetailsBinding;
import com.example.fooddelivery.ui.viewmodel.DetailsViewModel;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private DetailsViewModel detailsViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater,container,false);




        DetailsFragmentArgs bundle = DetailsFragmentArgs.fromBundle(getArguments());
        Foods food = bundle.getFood();

        Login login = bundle.getFavorite();
        Log.e("loginsonuc",login.getFood_name() + " boş" );

        binding.textViewFoodNameDetail.setText(food.getYemek_adi());
        binding.textViewPriceDetail.setText("Total Price: "+ food.getYemek_fiyat() +  "₺");

        String imageName = food.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + imageName;
        Glide.with(this).load(url).override(300,400).into(binding.imageView);


        binding.buttonBackToMain.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathDetailsToMain);
        });

        binding.imageButtonAddCart2.setOnClickListener(v->{

            String yemek_adi = food.getYemek_adi();
            String yemek_resim_adi = food.getYemek_resim_adi();
            int yemek_fiyat = food.getYemek_fiyat();
            String yemek_siparis_str = String.valueOf(binding.textViewAmountMainPage3.getText());
            int yemek_siparis_adet = Integer.parseInt(yemek_siparis_str);
            String kullanici_adi = "mert_yazici";

//            amount.setValue(yemek_siparis_adet);

            detailsViewModel.addCart(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);


            @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.add_cart_info, null);
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true;
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
            popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0);

            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        });

        binding.imageButtonIncreaseMainPage3.setOnClickListener(v -> {
            String food_amount_str_before = binding.textViewAmountMainPage3.getText().toString();
            int food_amount_int = Integer.parseInt(food_amount_str_before);
            food_amount_int ++;
            String food_amount = String.valueOf(food_amount_int);
            binding.textViewAmountMainPage3.setText(food_amount);
        });
        binding.imageButtonDecreaseMainPage3.setOnClickListener(v -> {
            String food_amount_str_before = binding.textViewAmountMainPage3.getText().toString();
            int food_amount_int = Integer.parseInt(food_amount_str_before);
            if (food_amount_int>0) {
                food_amount_int--;
                String food_amount = String.valueOf(food_amount_int);
                binding.textViewAmountMainPage3.setText(food_amount);
            }
        });

        binding.imageButtonFavorite.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathDetailsToFav);
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
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
    }
}