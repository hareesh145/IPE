package com.indiapoliticaledge.ui.superadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.ui.AddNewMLAScreen;
import com.indiapoliticaledge.ui.ViewMLAAdapter;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMLAListFragment extends Fragment {
    RecyclerView view_mlas_list;
    FloatingActionButton add_mla_button;
    private RetrofitAPI retrofitAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_mla_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view_mlas_list = view.findViewById(R.id.view_mlas_list);
        add_mla_button = view.findViewById(R.id.add_mla_button);

        retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");
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
//                ArrayList<UserInfo> usersList = new ArrayList<>();
//                view_mlas_list.setAdapter(new ViewMLAAdapter(ViewMLAListScreen.this, usersList));
            }
        });

        add_mla_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), AddNewMLAScreen.class));
            }
        });
    }
}
