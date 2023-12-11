package com.indiapoliticaledge.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.utils.Utils;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMLAInfoScreen extends AppCompatActivity {

    private TabLayout tabLayout;
    private TextView mla_name, constituency_txt, mandal_name_value, district_name_value;
    String[] tabs = new String[]{
            "Images", "Videos"
    };
    ViewPager2 viewPager;
    CircleImageView profile_image;
    private CircleImageView party_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mla_info_layout);
        initFields();
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
                            bindUserInfo(viewMemberResponse);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ViewMemberResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(viewPagerAdapter);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabs[position])).attach();
    }

    private void bindUserInfo(ViewMemberResponse viewMemberResponse) {
        mla_name.setText(viewMemberResponse.userInfo.firstName + " " + viewMemberResponse.userInfo.lastName);
        constituency_txt.setText(viewMemberResponse.constituencyMap);
        mandal_name_value.setText(viewMemberResponse.userInfo.constituency.constituencyName);
        district_name_value.setText(viewMemberResponse.userInfo.mobileNumber);

        Glide.with(this).load(viewMemberResponse.userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_user_logo).into(profile_image);
        Glide.with(this).load(viewMemberResponse.userInfo.getPartyLogo()).placeholder(R.drawable.ic_user_logo).into(party_icon);
    }

    private void initFields() {
        mla_name = findViewById(R.id.mla_name);
        constituency_txt = findViewById(R.id.constituency_txt);
        mandal_name_value = findViewById(R.id.mandal_name_value);
        district_name_value = findViewById(R.id.district_name_value);
        profile_image = findViewById(R.id.profile_image);
        party_icon = findViewById(R.id.party_icon);
    }
}
