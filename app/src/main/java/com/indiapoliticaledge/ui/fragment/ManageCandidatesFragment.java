package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ManageCandidatesLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.CandidatesList;
import com.indiapoliticaledge.network.responsemodel.CandidatesResponse;
import com.indiapoliticaledge.ui.CandidateProfile;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.ViewCandidatesAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCandidatesFragment extends Fragment {
    RecyclerView view_mlas_list;
    private RetrofitAPI retrofitAPI;

    ManageCandidatesLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ManageCandidatesLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_mlas_list = view.findViewById(R.id.view_mlas_list);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);

        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
        retrofitAPI.getAllCandidatesList(jsonObject).enqueue(new Callback<CandidatesResponse>() {
            @Override
            public void onResponse(Call<CandidatesResponse> call, Response<CandidatesResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().successCode.equals("200")) {
                        ArrayList<CandidatesList> usersList = response.body().candidatesList;
                        if (usersList.size() > 0) {
                            view_mlas_list.setAdapter(new ViewCandidatesAdapter(requireActivity(), usersList));
                            binding.noDataFoundTxt.setVisibility(View.GONE);
                        } else {
                            binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                            binding.noDataFoundTxt.setText(getString(R.string.no_candidates_data));
                        }
                    } else {
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                        binding.noDataFoundTxt.setText(getString(R.string.no_candidates_data));
                    }
                }
            }

            @Override
            public void onFailure(Call<CandidatesResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


        view.findViewById(R.id.add_mla_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MLAInfoDrawerScreen) {
                    CandidateProfile addLatestNewsFragment = new CandidateProfile();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addLatestNewsFragment, bundle.getString(Constants.USER_INFO));
                }
            }
        });
    }
}
