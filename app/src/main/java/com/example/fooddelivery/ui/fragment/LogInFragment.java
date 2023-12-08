package com.example.fooddelivery.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentLogInBinding;


public class LogInFragment extends Fragment {
    public FragmentLogInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLogInBinding.inflate(inflater,container,false);

//        binding.imageViewLogo.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        binding.buttonSignupPage.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathSignup);
        });
        return binding.getRoot();
    }
}