package com.indiapoliticaledge.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final FragmentManager fragmentManager;
    private final Lifecycle lifecycle;

    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

        this.fragmentManager = fragmentManager;
        this.lifecycle = lifecycle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ImagesFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}


