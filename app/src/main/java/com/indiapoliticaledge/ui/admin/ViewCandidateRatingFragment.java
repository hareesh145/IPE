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
import com.indiapoliticaledge.network.responsemodel.LeaderRatingResponse;
import com.indiapoliticaledge.ui.adapter.ViewCandidatesRatingAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCandidateRatingFragment extends Fragment {

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

        binding.addMlaButton.setVisibility(View.GONE);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);

        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());

        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().getLeaderRating(jsonObject).enqueue(new Callback<LeaderRatingResponse>() {
            @Override
            public void onResponse(Call<LeaderRatingResponse> call, Response<LeaderRatingResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    binding.viewMlasList.setAdapter(new ViewCandidatesRatingAdapter(requireActivity(), response.body().userRatingsList));
                }
            }

            @Override
            public void onFailure(Call<LeaderRatingResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }
}
