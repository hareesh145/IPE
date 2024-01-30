package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.SliderFragmentLayoutBinding;

public class ScreenSlidePageFragment extends Fragment {
    SliderFragmentLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SliderFragmentLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(requireContext()).load(R.drawable.spalsh_screen_en_img).into(binding.slideImage);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), LoginScreen.class);
                startActivity(intent);
                (requireActivity()).finish();
            }
        });
    }
}
