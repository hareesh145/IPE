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
import com.indiapoliticaledge.network.responsemodel.VDevelopmentResponse;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.adapter.ConstituencyDevelopmentAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageDevlopmentFragment extends Fragment {
    ManageCandidatesLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
                    AddConstituencyDevelopmentFragment addLatestNewsFragment = new AddConstituencyDevelopmentFragment();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addLatestNewsFragment, bundle.getString(Constants.USER_INFO));
                }
            }
        });


        Utils.showProgessBar(requireContext());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userInfo.getUserId());
        jsonObject.addProperty("constituencyId", userInfo.constituencyId);
        jsonObject.addProperty("deleteFlag", "N");
        RetrofitClient.getInstance(requireContext()).getRetrofitAPI().viewConstituencyDevelopment(jsonObject).enqueue(new Callback<VDevelopmentResponse>() {
            @Override
            public void onResponse(Call<VDevelopmentResponse> call, Response<VDevelopmentResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: " + response.body());
                    if (response.body().constituencyDepartmentsList != null && response.body().constituencyDepartmentsList.size() > 0) {
                        binding.viewMlasList.setAdapter(new ConstituencyDevelopmentAdapter(requireActivity(), response.body().constituencyDepartmentsList));
                    } else {

                    }
                }

            }

            @Override
            public void onFailure(Call<VDevelopmentResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }
}
