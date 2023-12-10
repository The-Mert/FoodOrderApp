package com.example.fooddelivery.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.CartFoods;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.repo.FoodsDaoRepository;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.ui.fragment.MainPageFragment;
import com.example.fooddelivery.ui.fragment.MainPageFragmentDirections;
import com.example.fooddelivery.ui.viewmodel.CartViewModel;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MainMenuCardHolding> {
    private List<Foods> foodsList;
    private Context context;
    private MainPageViewModel viewModel;



    public OrdersAdapter(List<Foods> foodsList, Context context, MainPageViewModel viewModel) {
        this.foodsList = foodsList;
        this.context = context;
        this.viewModel = viewModel;

    }

    @NonNull
    @Override
    public MainMenuCardHolding onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainMenuCardBinding mainMenu = MainMenuCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MainMenuCardHolding(mainMenu);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuCardHolding holder, int position) {
        Foods foods = foodsList.get(position);
        MainMenuCardBinding m = holder.bindingMenu;



        //Food Types
        if(foods.getYemek_adi().equals("Ayran") || foods.getYemek_adi().equals("Fanta") ||foods.getYemek_adi().equals("Su") ||foods.getYemek_adi().equals("Kahve")){
            m.textViewFoodTypeMainPage.setText("Beverages");
        }else if (foods.getYemek_adi().equals("Baklava") || foods.getYemek_adi().equals("Kadayıf")|| foods.getYemek_adi().equals("Sütlaç") || foods.getYemek_adi().equals("Tiramisu")){
            m.textViewFoodTypeMainPage.setText("Dessert");
        }else{
            m.textViewFoodTypeMainPage.setText("Dinner");
        }
        //-----------

        //Image Resource
        String imageName = foods.getYemek_resim_adi();
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + imageName;
        Glide.with(context).load(url).override(300,400).into(m.imageViewFoodMainPage);
        //-----------


        //Name
        m.textViewFoodNameMainPage.setText(foods.getYemek_adi());
        //-----------

        //Fiyat
        m.textViewPriceMainPage.setText(foods.getYemek_fiyat()+ "₺");
        //-----------

        //Button Increase And Decrease
        m.imageButtonIncreaseMainPage.setOnClickListener(v -> {  // Increase food in cart
            String food_amount_str_before = m.textViewAmountMainPage.getText().toString();
            int food_amount_int = Integer.parseInt(food_amount_str_before);
            food_amount_int ++;
            String food_amount = String.valueOf(food_amount_int);
            m.textViewAmountMainPage.setText(food_amount);
        });

        m.imageButtonDecreaseMainPage.setOnClickListener(v -> {  //Decrease food in cart
            String food_amount_str_before = m.textViewAmountMainPage.getText().toString();
            int food_amount_int = Integer.parseInt(food_amount_str_before);
            if (food_amount_int>0){
            food_amount_int --;
            String food_amount = String.valueOf(food_amount_int);
            m.textViewAmountMainPage.setText(food_amount);
            }
        });
        //-----------

        //Add Cart
        m.imageButtonAddCart.setOnClickListener(v -> {
            String yemek_adi = foods.getYemek_adi();
            String yemek_resim_adi = foods.getYemek_resim_adi();
            int yemek_fiyat = foods.getYemek_fiyat();
            String yemek_siparis_str = String.valueOf(m.textViewAmountMainPage.getText());
            int yemek_siparis_adet = Integer.parseInt(yemek_siparis_str);
            String kullanici_adi = "mert_yazici";


            viewModel.addCart(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);
            Log.e("Kontrol: " , foods.getYemek_adi()+"");
        });
        //-----------


        m.imageViewFoodMainPage.setOnClickListener(v -> {       // Path to Details Fragment
            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods);
            Navigation.findNavController(v).navigate(R.id.pathDetails);

        });




//        m.imageViewFoodMainPage.setImageResource();
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }

    public class MainMenuCardHolding extends RecyclerView.ViewHolder{
        private MainMenuCardBinding bindingMenu;

        public MainMenuCardHolding(MainMenuCardBinding bindingMenu) {
            super(bindingMenu.getRoot());
            this.bindingMenu = bindingMenu;
        }
    }
}
