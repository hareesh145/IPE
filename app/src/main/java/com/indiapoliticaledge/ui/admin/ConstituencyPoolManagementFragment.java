package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.indiapoliticaledge.databinding.PoolManagementLayoutBinding;
import com.indiapoliticaledge.model.BoothLevelResponse;
import com.indiapoliticaledge.model.PollManagementResponse;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.adapter.BoothLevelAdapter;
import com.indiapoliticaledge.ui.adapter.PollManagementAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyPoolManagementFragment extends Fragment {

    PoolManagementLayoutBinding binding;
    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PoolManagementLayoutBinding.inflate(inflater, container, false);
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
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().getPollManagement(jsonObject).enqueue(new Callback<PollManagementResponse>() {
            @Override
            public void onResponse(Call<PollManagementResponse> call, Response<PollManagementResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body().pollManagementList != null && response.body().pollManagementList.size() > 0) {
                        binding.noDataFoundTxt.setVisibility(View.GONE);
                        binding.boothLevelList.setAdapter(new PollManagementAdapter(requireActivity(), response.body().pollManagementList, userInfo));
                    } else {
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<PollManagementResponse> call, Throwable t) {
                Utils.hideProgessBar();

            }
        });

        binding.addMlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MLAInfoDrawerScreen) {
                    AddPollCenterFragment addBoothPoolManagement = new AddPollCenterFragment();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addBoothPoolManagement, bundle.getString(Constants.USER_INFO));
                }
            }
        });

        binding.searchGroupName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {


                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.searchGroupName.getText().toString() != null && binding.searchGroupName.getText().toString().length() > 0) {
                    searchPollCenter(binding.searchGroupName.getText().toString());
                }
            }
        });
    }

    private void searchPollCenter(String pollCenter) {
        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        jsonObject.addProperty("pollCenterName", pollCenter);
        jsonObject.addProperty("deleteFlag", "N");
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().getPollManagement(jsonObject).enqueue(new Callback<PollManagementResponse>() {
            @Override
            public void onResponse(Call<PollManagementResponse> call, Response<PollManagementResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body().pollManagementList != null && response.body().pollManagementList.size() > 0) {
                        binding.noDataFoundTxt.setVisibility(View.GONE);
                        binding.boothLevelList.setAdapter(new PollManagementAdapter(requireActivity(), response.body().pollManagementList, userInfo));
                    } else {
                        binding.noDataFoundTxt.setText(getString(R.string.no_poll_centers));
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<PollManagementResponse> call, Throwable t) {
                Utils.hideProgessBar();

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
