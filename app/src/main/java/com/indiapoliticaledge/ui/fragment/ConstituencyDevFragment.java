package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.FilteredVCDByYearsResponse;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyDevFragment extends Fragment {
    private static final String TAG = ConstituencyDevFragment.class.getSimpleName();
    private TextView no_data_found_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.constutiency_dev_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        no_data_found_txt = view.findViewById(R.id.no_data_found_txt);
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
        jsonObject.addProperty("year", 2022);
        jsonObject.addProperty("filterYear", 2023);
        jsonObject.addProperty("deleteFlag", "N");
        Utils.showProgessBar(requireContext());
        retrofitAPI.viewConstituencyDevByYears(jsonObject).enqueue(new Callback<FilteredVCDByYearsResponse>() {
            @Override
            public void onResponse(Call<FilteredVCDByYearsResponse> call, Response<FilteredVCDByYearsResponse> response) {
                try {
                    Utils.hideProgessBar();
                    Log.d(TAG, "onResponse: " + response.body());
                    no_data_found_txt.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FilteredVCDByYearsResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


    }
}
