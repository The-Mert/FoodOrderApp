package com.example.fooddelivery.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddelivery.R;
import com.example.fooddelivery.databinding.FragmentMainPageBinding;
import com.example.fooddelivery.databinding.FragmentPaymentBinding;


public class PaymentFragment extends Fragment {
    private FragmentPaymentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}