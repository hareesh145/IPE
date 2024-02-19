package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.BoothPoolManagementLayoutBinding;
import com.indiapoliticaledge.model.BoothLevelResponse;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.adapter.BoothLevelAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoothPoolManagementFragment extends Fragment {

    BoothPoolManagementLayoutBinding binding;
    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BoothPoolManagementLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);

        bindMlaInfo(userInfo);

        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        jsonObject.addProperty("deleteFlag", "N");
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().getBoothLevelManagement(jsonObject).enqueue(new Callback<BoothLevelResponse>() {
            @Override
            public void onResponse(Call<BoothLevelResponse> call, Response<BoothLevelResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body().boothlevelMgmtList != null && response.body().boothlevelMgmtList.size() > 0) {
                        binding.boothLevelList.setAdapter(new BoothLevelAdapter(requireActivity(), response.body().boothlevelMgmtList, userInfo));
                    }
                }
            }

            @Override
            public void onFailure(Call<BoothLevelResponse> call, Throwable t) {
                Utils.hideProgessBar();

            }
        });

        binding.addMlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MLAInfoDrawerScreen) {
                    AddBoothPoolManagement addBoothPoolManagement = new AddBoothPoolManagement();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addBoothPoolManagement, bundle.getString(Constants.USER_INFO));
                }
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.searchGroupName.getText().toString().isEmpty()) return;
                searchBoothLevelGroup(binding.searchGroupName.getText().toString());
            }
        });
    }

    private void searchBoothLevelGroup(String searchGrp) {
        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        jsonObject.addProperty("groupName", searchGrp);
        jsonObject.addProperty("deleteFlag", "N");
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().searchBoothManagement(jsonObject).enqueue(new Callback<BoothLevelResponse>() {
            @Override
            public void onResponse(Call<BoothLevelResponse> call, Response<BoothLevelResponse> response) {
                try {
                    Utils.hideProgessBar();
                    if (response.isSuccessful()) {
                        if (response.body().boothlevelMgmtList != null && response.body().boothlevelMgmtList.size() > 0) {
                            binding.boothLevelList.setAdapter(new BoothLevelAdapter(requireActivity(), response.body().boothlevelMgmtList, userInfo));
                            binding.noDataFoundTxt.setVisibility(View.GONE);
                            binding.boothLevelList.setVisibility(View.VISIBLE);
                        } else {
                            binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                            binding.boothLevelList.setVisibility(View.GONE);
                            binding.noDataFoundTxt.setText(getString(R.string.no_booth_level_grp));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BoothLevelResponse> call, Throwable t) {

            }
        });
    }

    private void bindMlaInfo(UserInfo userInfo) {
        binding.mlaName.setText(userInfo.firstName + " " + userInfo.lastName);
        binding.constituencyNameTxt.setText(userInfo.constituency.constituencyName);
        binding.partyNameTxt.setText(userInfo.getPartyName());
        if (userInfo.userConstituencies != null) {
            if (userInfo.userConstituencies.startDate != null) {
                binding.startDateValueTxt.setText(userInfo.userConstituencies.startDate.split("\\s")[0]);
            }
            if (userInfo.userConstituencies.endDate != null) {
                binding.endDateValueTxt.setText(userInfo.userConstituencies.endDate.split("\\s")[0]);
            }
        } else {
            if (userInfo.startDate != null) {
                binding.startDateValueTxt.setText(userInfo.startDate.split("\\s")[0]);
            }
            if (userInfo.endDate != null) {
                binding.endDateValueTxt.setText(userInfo.endDate.split("\\s")[0]);
            }
        }
        Glide.with(this).load(userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_logo).into(binding.profileImage);
        Glide.with(this).load(userInfo.getPartyLogo()).placeholder(R.drawable.ic_logo).into(binding.partyIcon);
    }
}
