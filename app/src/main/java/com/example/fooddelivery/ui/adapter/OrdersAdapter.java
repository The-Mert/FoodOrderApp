package com.example.fooddelivery.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery.R;
import com.example.fooddelivery.data.entity.Foods;
import com.example.fooddelivery.data.entity.Login;
import com.example.fooddelivery.databinding.MainMenuCardBinding;
import com.example.fooddelivery.ui.fragment.MainPageFragmentDirections;
import com.example.fooddelivery.ui.viewmodel.MainPageViewModel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MainMenuCardHolding> {
    private List<Foods> foodsList;
    private List<Foods> filterFoods;
    private final Context context;
    private final MainPageViewModel viewModel;
    public MutableLiveData<Integer> sumAmount = new MutableLiveData<>(0);
    public MutableLiveData<Integer> dumPrice = new MutableLiveData<>(0);




    public OrdersAdapter(List<Foods> foodsList, Context context, MainPageViewModel viewModel) {
        this.foodsList = foodsList;
        this.context = context;
        this.viewModel = viewModel;
        this.filterFoods = new ArrayList<>(foodsList);
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

        m.imageButtonLikeMainPage.setOnClickListener(v -> {

                m.imageButtonLikeMainPage.setColorFilter(ContextCompat.getColor(context, R.color.login_signup));
                String food_name = foods.getYemek_adi();
                String img_name = foods.getYemek_resim_adi();
                String food_price = foods.getYemek_fiyat() + "₺";
                viewModel.addFav("mertyzc_reveiced@hotmail.com", "mert", "123456", food_name, img_name, food_price, "mert_yazici");
            @SuppressLint("InflateParams") View popupView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.popup_favorites, null);
            boolean focusable = true;
            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,focusable);

            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });




        });
//        SharedPreferences sharedPreferencesOrder = context.getSharedPreferences("Order", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editorOrder = sharedPreferencesOrder.edit();

        //Add Cart
        m.imageButtonAddCart.setOnClickListener(v -> {
            String yemek_adi = foods.getYemek_adi();
            String yemek_resim_adi = foods.getYemek_resim_adi();
            int yemek_fiyat = foods.getYemek_fiyat();
            String yemek_siparis_str = String.valueOf(m.textViewAmountMainPage.getText());
            int yemek_siparis_adet = Integer.parseInt(yemek_siparis_str);
            String kullanici_adi = "mert_yazici";

            viewModel.addCart(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi);



//                editorOrder.putInt("orderAmount",yemek_siparis_adet + sumAmount.getValue() );
//                editorOrder.putInt("orderPrice", yemek_fiyat + dumPrice.getValue());




            @SuppressLint("InflateParams") View popupView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.add_cart_info, null);
            boolean focusable = true;
            final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,focusable);

            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });



        });
        //-----------

        m.imageViewFoodMainPage.setOnClickListener(v -> {       // Path to Details Fragment
            Login login = new Login();
            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods,login);
            Navigation.findNavController(v).navigate(pathDetails);

        });
        m.textViewFoodNameMainPage.setOnClickListener(v -> {       // Path to Details Fragment
            Login login = new Login();
            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods,login);
            Navigation.findNavController(v).navigate(pathDetails);

        });
        m.textViewFoodTypeMainPage.setOnClickListener(v -> {       // Path to Details Fragment
            Login login = new Login();
            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods,login);
            Navigation.findNavController(v).navigate(pathDetails);

        });
        m.textViewPriceMainPage.setOnClickListener(v -> {       // Path to Details Fragment
            Login login = new Login();
            MainPageFragmentDirections.PathDetails pathDetails = MainPageFragmentDirections.pathDetails(foods,login);
            Navigation.findNavController(v).navigate(pathDetails);

        });

    }
    @SuppressLint("NotifyDataSetChanged")
    public void searchFilter(String query) {
        foodsList.clear();
        if (query.trim().isEmpty()) {
            foodsList.addAll(filterFoods);
        } else {
            query = query.toLowerCase(new Locale("tr", "TR"));
            for (Foods food : filterFoods) {
                if (normalizeString(food.getYemek_adi()).contains(normalizeString(query))) {
                    foodsList.add(food);
                }
            }
        }
        notifyDataSetChanged();
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase(new Locale("tr", "TR"));
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
