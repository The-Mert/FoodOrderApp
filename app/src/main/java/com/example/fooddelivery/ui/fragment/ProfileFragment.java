package com.example.fooddelivery.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        binding.imageButtonLogOutProfile.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.pathLogout);
        });
        binding.imageButtonProfileToHome.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.pathProfileToMain);
        });



        return binding.getRoot();
    }
}