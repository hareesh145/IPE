package com.indiapoliticaledge.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.ui.fragment.MLAInfoFragment;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMLAInfoScreen extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mla_info_layout);

        Utils.showProgessBar(this);
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(this).getRetrofitAPI();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", getIntent().getExtras().getInt("user_id", -1));
        retrofitAPI.getMember(jsonObject).enqueue(new Callback<ViewMemberResponse>() {
            @Override
            public void onResponse(Call<ViewMemberResponse> call, Response<ViewMemberResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    ViewMemberResponse viewMemberResponse = response.body();
                    if (viewMemberResponse != null && viewMemberResponse.successCode.equals("200")) {
                        UserInfo userInfo = viewMemberResponse.getUserInfo();
                        if (userInfo != null) {
//                            bindUserInfo(viewMemberResponse);
                            showViewMLA(userInfo);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ViewMemberResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
//        viewPager = findViewById(R.id.viewPager);
//        tabLayout = findViewById(R.id.tab_layout);
//        viewPager.setAdapter(viewPagerAdapter);
//
//
//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabs[position])).attach();
    }

    private void showViewMLA(UserInfo userInfo) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MLAInfoFragment mlaInfoFragment = new MLAInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.USER_INFO, new Gson().toJson(userInfo));
        mlaInfoFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_container, mlaInfoFragment).commitAllowingStateLoss();
    }
}
