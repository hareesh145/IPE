package com.indiapoliticaledge.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.ui.CandidateProfile;
import com.indiapoliticaledge.ui.ViewCandidatesAdapter;
import com.indiapoliticaledge.ui.ViewMLAAdapter;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCandidatesFragment extends Fragment {
    RecyclerView view_mlas_list;
    private RetrofitAPI retrofitAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manage_candidates_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_mlas_list = view.findViewById(R.id.view_mlas_list);

        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");
        retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
        retrofitAPI.manageMembers(jsonObject).enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(Call<MembersResponse> call, Response<MembersResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().successCode.equals("200")) {
                        ArrayList<UserInfo> usersList = response.body().usersList;
                        view_mlas_list.setAdapter(new ViewMLAAdapter(requireActivity(), usersList));
                    }
                }
            }

            @Override
            public void onFailure(Call<MembersResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });



        view_mlas_list.setAdapter(new ViewCandidatesAdapter(requireActivity(), new ArrayList<>()));
        view.findViewById(R.id.add_mla_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), CandidateProfile.class));
            }
        });
    }
}
