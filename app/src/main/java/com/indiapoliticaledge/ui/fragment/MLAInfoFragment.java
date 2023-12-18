package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MLAInfoFragment extends Fragment {
    private TextView mla_name, constituency_txt, start_date_value_txt, end_date_value_txt;
    ImageView profile_image,party_icon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mla_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFields(view);
        Utils.showProgessBar(requireActivity());
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
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
    }

    private void initFields(View view) {
        mla_name = view.findViewById(R.id.mla_name);
        constituency_txt = view.findViewById(R.id.constituency_name_txt);
        start_date_value_txt = view.findViewById(R.id.start_date_value_txt);
        end_date_value_txt = view.findViewById(R.id.end_date_value_txt);
        profile_image = view.findViewById(R.id.profile_image);
        party_icon = view.findViewById(R.id.party_icon);
    }

    private void bindUserInfo(ViewMemberResponse viewMemberResponse) {
        UserInfo userInfo = viewMemberResponse.userInfo;
        mla_name.setText(userInfo.firstName + " " + userInfo.lastName);
        constituency_txt.setText(viewMemberResponse.constituencyMap);
        if (userInfo.userConstituencies != null) {
            if (userInfo.userConstituencies.startDate != null) {
                start_date_value_txt.setText(userInfo.userConstituencies.startDate.split("\\s")[0]);
            }
            if (userInfo.userConstituencies.endDate != null) {
                end_date_value_txt.setText(userInfo.userConstituencies.endDate.split("\\s")[0]);
            }
        } else {
            if (userInfo.startDate != null) {
                start_date_value_txt.setText(userInfo.startDate.split("\\s")[0]);
            }
            if (userInfo.endDate != null) {
                end_date_value_txt.setText(userInfo.endDate.split("\\s")[0]);
            }
        }
        Glide.with(this).load(viewMemberResponse.userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_logo).into(profile_image);
        Glide.with(this).load(viewMemberResponse.userInfo.getPartyLogo()).placeholder(R.drawable.ic_logo).into(party_icon);
//        start_date_value_txt.setText(viewMemberResponse.userConstituencies.startDate.split("\\s")[0]);
//        end_date_value_txt.setText(viewMemberResponse.userInfo.userConstituencies.endDate.split("\\s")[0]);
    }
}
