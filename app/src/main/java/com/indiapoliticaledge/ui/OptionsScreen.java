package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.indiapoliticaledge.R;

public class OptionsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);


        findViewById(R.id.candidate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(OptionsScreen.this, MLAInfoDrawerScreen.class));
            }
        });

        findViewById(R.id.super_admin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsScreen.this, MLAInfoDrawerScreen.class));
            }
        });

        findViewById(R.id.admin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsScreen.this, ViewMLAListScreen.class));
            }
        });
    }
}
