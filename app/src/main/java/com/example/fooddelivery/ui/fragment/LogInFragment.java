package com.example.fooddelivery.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentLogInBinding;
import com.example.fooddelivery.ui.viewmodel.FavoriteViewModel;
import com.example.fooddelivery.ui.viewmodel.LogInViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LogInFragment extends Fragment {
    public FragmentLogInBinding binding;

    private LogInViewModel logInViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater,container,false);



        binding.buttonSignupPage.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathSignup);
        });

        binding.logInButton.setOnClickListener(v -> {
            String username = binding.editTextUsernLog.getText().toString();
            String password = binding.editTextPassLog.getText().toString();
            if(username.matches("mertyzc") && password.matches("159357mrt")){
                Navigation.findNavController(v).navigate(R.id.pathSuccesLog);
            }
            else {
                @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.pupupwindows, null);
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
                binding.editTextUsernLog.setText("");
                binding.editTextPassLog.setText("");
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
    }
}