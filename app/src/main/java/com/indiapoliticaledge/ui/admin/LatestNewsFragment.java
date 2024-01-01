package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.databinding.LatestNewsFragmentBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.LatestNewsResponse;
import com.indiapoliticaledge.ui.adapter.LatestNewsAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestNewsFragment extends Fragment {

    LatestNewsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LatestNewsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());

        RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().getAllLatestNews(jsonObject).enqueue(new Callback<LatestNewsResponse>() {
            @Override
            public void onResponse(Call<LatestNewsResponse> call, Response<LatestNewsResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    binding.constituencyDevList.setAdapter(new LatestNewsAdapter(requireActivity(), response.body().newsList));
                }
            }

            @Override
            public void onFailure(Call<LatestNewsResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });

    }
}
