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
import com.indiapoliticaledge.databinding.ConstutiencyDevFragmentBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.VDevelopmentResponse;
import com.indiapoliticaledge.ui.adapter.ViewConstituencyDevAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyDevFragment extends Fragment {
    private static final String TAG = ConstituencyDevFragment.class.getSimpleName();
    private TextView no_data_found_txt;
    ConstutiencyDevFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ConstutiencyDevFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
        jsonObject.addProperty("userId", userInfo.getUserId());
        jsonObject.addProperty("constituencyId", userInfo.constituencyId);
        jsonObject.addProperty("deleteFlag", "N");
        Utils.showProgessBar(requireContext());
        retrofitAPI.viewConstituencyDevelopment(jsonObject).enqueue(new Callback<VDevelopmentResponse>() {
            @Override
            public void onResponse(Call<VDevelopmentResponse> call, Response<VDevelopmentResponse> response) {
                try {
                    Utils.hideProgessBar();
                    Log.d(TAG, "onResponse: " + response.body());
                    if (response.body().constituencyDepartmentsList != null && response.body().constituencyDepartmentsList.size() > 0) {
                        binding.constituencyDevList.setAdapter(new ViewConstituencyDevAdapter(requireActivity(), response.body().constituencyDepartmentsList));
                    }
//                    no_data_found_txt.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VDevelopmentResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


    }
}
