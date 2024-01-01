package com.indiapoliticaledge.ui.admin;

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
import com.indiapoliticaledge.databinding.ManageCandidatesLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.CandidateDonateResponse;
import com.indiapoliticaledge.ui.adapter.ViewCandidateDonationAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCandidateDonationsFrgment extends Fragment {
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

        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI()
                .viewCandidateDonate(jsonObject).enqueue(new Callback<CandidateDonateResponse>() {
                    @Override
                    public void onResponse(Call<CandidateDonateResponse> call, Response<CandidateDonateResponse> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            Log.d("TAG", "onResponse: " + response.body().getCandidatesDonationsList());

                            binding.totalDonationAmount.setVisibility(View.VISIBLE);
                            binding.totalDonationAmountValue.setVisibility(View.VISIBLE);

                            binding.totalDonationAmountValue.setText("" + response.body().getTotalDonationAmount());

                            binding.viewMlasList.setAdapter(new ViewCandidateDonationAdapter(requireActivity(), response.body().getCandidatesDonationsList()));
                        }

                    }

                    @Override
                    public void onFailure(Call<CandidateDonateResponse> call, Throwable t) {
                        Utils.hideProgessBar();
                    }
                });
    }
}
