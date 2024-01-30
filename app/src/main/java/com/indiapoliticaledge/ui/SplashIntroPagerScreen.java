package com.indiapoliticaledge.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.indiapoliticaledge.databinding.SplashViewPagerLayoutBinding;

public class SplashIntroPagerScreen extends AppCompatActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    SplashViewPagerLayoutBinding binding;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashViewPagerLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        binding.imagesViewPager.setAdapter(screenSlidePagerAdapter);

    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return new ScreenSlideTePageFragment();
            }
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
