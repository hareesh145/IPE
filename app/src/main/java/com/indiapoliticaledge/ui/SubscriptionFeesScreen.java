package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.indiapoliticaledge.R;

public class SubscriptionFeesScreen extends AppCompatActivity {
    Button submit_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription_fees_layout);

        submit_btn = findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(v -> {
            startActivity(new Intent(SubscriptionFeesScreen.this, ViewMLAListScreen.class));
        });
    }
}
