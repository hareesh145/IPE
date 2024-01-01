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
import com.indiapoliticaledge.databinding.ManageCandidatesLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.OpinionResponse;
import com.indiapoliticaledge.ui.adapter.ViewOpinionPollingAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpinionPollingFragment extends Fragment {

    ManageCandidatesLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ManageCandidatesLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        Utils.showProgessBar(requireContext());
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().manageOpinions(jsonObject).enqueue(new Callback<OpinionResponse>() {
            @Override
            public void onResponse(Call<OpinionResponse> call, Response<OpinionResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    binding.viewMlasList.setAdapter(new ViewOpinionPollingAdapter(requireActivity(), response.body().userOpinionsList));
                }
            }

            @Override
            public void onFailure(Call<OpinionResponse> call, Throwable t) {
                Utils.hideProgessBar();

            }
        });
    }
}
