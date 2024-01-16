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
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ManageCandidatesLayoutBinding;
import com.indiapoliticaledge.model.NoticeBoardResponse;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.adapter.NoticeMessagesAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardMessagesFragment extends Fragment {
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
        binding.addMlaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MLAInfoDrawerScreen) {
                    AddUpdateNoticeBoardFragment addUpdateNoticeBoardFragment = new AddUpdateNoticeBoardFragment();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addUpdateNoticeBoardFragment, bundle.getString(Constants.USER_INFO));
                }
            }
        });


        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        RetrofitClient.getInstance(requireActivity()).getRetrofitAPI()
                .getAllNoticeBoardMessages(jsonObject).enqueue(new Callback<NoticeBoardResponse>() {
                    @Override
                    public void onResponse(Call<NoticeBoardResponse> call, Response<NoticeBoardResponse> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            if (response.body().noticeMessagesList != null && response.body().noticeMessagesList.size() > 0) {
                                binding.viewMlasList.setAdapter(new NoticeMessagesAdapter(requireActivity(), response.body().noticeMessagesList, bundle.getString(Constants.USER_INFO)));
                                binding.noDataFoundTxt.setVisibility(View.GONE);
                            } else {
                                binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                                binding.noDataFoundTxt.setText(getString(R.string.no_notice_messages_data));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NoticeBoardResponse> call, Throwable t) {
                        Utils.hideProgessBar();
                    }
                });
    }
}
