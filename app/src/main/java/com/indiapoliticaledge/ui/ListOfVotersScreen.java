package com.indiapoliticaledge.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ListOfVotersLayoutBinding;
import com.indiapoliticaledge.model.BoothLevelManagmentResponse;
import com.indiapoliticaledge.model.BoothlevelMgmtInfo;
import com.indiapoliticaledge.model.PollManagementList;
import com.indiapoliticaledge.model.PollManagmentVotersResponse;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.adapter.VotersListAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfVotersScreen extends AppCompatActivity {

    ListOfVotersLayoutBinding binding;
    UserInfo userInfo;
    BoothlevelMgmtInfo boothlevelMgmtInfo;
    PollManagementList pollManagementList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ListOfVotersLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userInfo = new Gson().fromJson(getIntent().getStringExtra(Constants.USER_INFO), UserInfo.class);
        boothlevelMgmtInfo = (BoothlevelMgmtInfo) getIntent().getSerializableExtra(Constants.BOOTH_LEVEL_MANEGEMENT);
        pollManagementList = (PollManagementList) getIntent().getSerializableExtra(Constants.POLL_MANEGEMENT);
        binding.customTitleView.titleTxt.setText(R.string.voters_information);

        binding.customTitleView.titleBar.setBackgroundColor(getResources().getColor(Utils.getLoggedInPartyColor(this, userInfo.getPartyName())));

        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        if (boothlevelMgmtInfo != null) {
            jsonObject.addProperty("boothlevelMgmtId", boothlevelMgmtInfo.boothlevelMgmtId);
            RetrofitClient.getInstance(this).getRetrofitAPI().viewGroupVoters(jsonObject).enqueue(new Callback<BoothLevelManagmentResponse>() {
                @Override
                public void onResponse(Call<BoothLevelManagmentResponse> call, Response<BoothLevelManagmentResponse> response) {
                    Utils.hideProgessBar();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().votersList != null
                                && response.body().votersList.size() > 0) {
                            binding.votersList.setAdapter(new VotersListAdapter(ListOfVotersScreen.this, response.body().votersList));
                        }
                    }
                }

                @Override
                public void onFailure(Call<BoothLevelManagmentResponse> call, Throwable t) {
                    Utils.hideProgessBar();
                }
            });
        } else {
            jsonObject.addProperty("pollManagementId", pollManagementList.pollManagementId);
            RetrofitClient.getInstance(this).getRetrofitAPI().getPollVoters(jsonObject).enqueue(new Callback<PollManagmentVotersResponse>() {
                @Override
                public void onResponse(Call<PollManagmentVotersResponse> call, Response<PollManagmentVotersResponse> response) {
                    Utils.hideProgessBar();
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().votersList != null
                                && response.body().votersList.size() > 0) {
                            binding.votersList.setAdapter(new VotersListAdapter(ListOfVotersScreen.this, response.body().votersList));
                        }
                    }
                }

                @Override
                public void onFailure(Call<PollManagmentVotersResponse> call, Throwable t) {
                    Utils.hideProgessBar();
                }
            });
        }



    }
}
