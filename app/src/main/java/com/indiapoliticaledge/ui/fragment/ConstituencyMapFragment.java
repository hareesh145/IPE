package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.ConstituencyMapResponse;
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

        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.userId);
        jsonObject.addProperty("constituencyId", userInfo.constituencyId);
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
