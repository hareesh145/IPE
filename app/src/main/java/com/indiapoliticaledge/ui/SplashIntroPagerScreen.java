package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.SplashViewPagerLayoutBinding;
import com.indiapoliticaledge.ui.adapter.SplashImageAdapter;

import java.util.ArrayList;

public class SplashIntroPagerScreen extends AppCompatActivity {

    SplashViewPagerLayoutBinding binding;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashViewPagerLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.spalsh_screen_en_img);
        integers.add(R.drawable.splash_screen_te_img);

        binding.recyclerViewImage.setAdapter(new SplashImageAdapter(this, integers));

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashIntroPagerScreen.this, LoginScreen.class));
            }
        });


//        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
//        binding.imagesViewPager.setAdapter(screenSlidePagerAdapter);

    }


}
