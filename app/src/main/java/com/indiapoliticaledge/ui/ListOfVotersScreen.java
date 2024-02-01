package com.indiapoliticaledge.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.indiapoliticaledge.databinding.ListOfVotersLayoutBinding;

public class ListOfVotersScreen extends AppCompatActivity {

    ListOfVotersLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListOfVotersLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }
}
