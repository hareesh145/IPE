package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.indiapoliticaledge.network.responsemodel.ConstituencyMapResponse;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.SuperAdminScreen;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyMapFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.constituency_map_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();

        TextView mla_name = view.findViewById(R.id.mla_name);
        TextView constituency_name_txt = view.findViewById(R.id.constituency_name_txt);
        TextView party_name_txt = view.findViewById(R.id.party_name_txt);
        TextView mobile_number_txt = view.findViewById(R.id.mobile_number_txt);
        ImageView profile_image = view.findViewById(R.id.profile_image);
        ImageView party_icon = view.findViewById(R.id.party_icon);

        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        Log.d("TAG", "onViewCreated: "+jsonObjectUser);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        mla_name.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
        constituency_name_txt.setText(userInfo.constituency.constituencyName);
        party_name_txt.setText(userInfo.getPartyName());
        mobile_number_txt.setText(userInfo.getMobileNumber());

        Glide.with(this).load(userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_user_logo).into(profile_image);

        Glide.with(this).load(userInfo.getPartyLogo()).placeholder(R.mipmap.ic_launcher).into(party_icon);

        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.userId);
        jsonObject.addProperty("constituencyId", userInfo.constituencyId);

        if (requireActivity() instanceof SuperAdminScreen) {
            ((SuperAdminScreen) requireActivity()).setTitleText("MLA Information");
        } else if (requireActivity() instanceof MLAInfoDrawerScreen) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText("MLA Information");
        }
        retrofitAPI.constituencyMap(jsonObject).enqueue(new Callback<ConstituencyMapResponse>() {
            @Override
            public void onResponse(Call<ConstituencyMapResponse> call, Response<ConstituencyMapResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    Log.d("TAG", " response.body() :::: " + response.body());
                }

            }

            @Override
            public void onFailure(Call<ConstituencyMapResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }
}
