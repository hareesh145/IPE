package com.indiapoliticaledge.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.utils.Constants;

public class BaseActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void createFragment(Fragment fragment, String userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USER_INFO, userInfo);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commitAllowingStateLoss();
    }
}
