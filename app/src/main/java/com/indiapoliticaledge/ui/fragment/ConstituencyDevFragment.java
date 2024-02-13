package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ConstutiencyDevFragmentBinding;
import com.indiapoliticaledge.model.DepartmentsList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.VDevelopmentResponse;
import com.indiapoliticaledge.ui.adapter.CustomSpinnerAdapter;
import com.indiapoliticaledge.ui.adapter.ViewConstituencyDevAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyDevFragment extends Fragment {
    private static final String TAG = ConstituencyDevFragment.class.getSimpleName();
    private TextView no_data_found_txt;
    ConstutiencyDevFragmentBinding binding;

    ArrayList<String> yearsList = new ArrayList<>();

    ArrayList<DepartmentsList> departmentsLists = new ArrayList<>();

    int departmentID = -1;
    int selectedYear = 0;

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

        setYearsList();


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
                        binding.noDataFoundTxt.setVisibility(View.GONE);
                    } else {
                        binding.constituencyDevList.setVisibility(View.GONE);
                        binding.noDataFoundTxt.setText(getString(R.string.no_constituency_department_data));
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                    }
                    if (departmentsLists.size() == 0) {
                        departmentsLists = response.body().departmentsList;
                    }
                    binding.constituencyDevSpinner.setAdapter(new CustomSpinnerAdapter(requireContext(), departmentsLists));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VDevelopmentResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });

        binding.yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    viewDepartmentAndYearDevelopment(departmentID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.constituencyDevSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    DepartmentsList departmentsList = (DepartmentsList) parent.getSelectedItem();
                    departmentID = departmentsList.departmentId;
                    viewDepartmentAndYearDevelopment(departmentID);
                } catch (Exception e) {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setYearsList() {
        yearsList.add(0, "Select Year");
        for (int i = 2018; i <= 2100; i++) {
            yearsList.add(String.valueOf(i));
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(requireContext(),
                R.layout.spinner_item, yearsList);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        binding.yearsSpinner.setAdapter(stringArrayAdapter);
        binding.yearsSpinner.setSelection(0);
    }

    private void viewDepartmentAndYearDevelopment(int departmentId) {
        if (binding.yearsSpinner.getSelectedItemPosition() == 0)
            return;
        if (departmentId == -1)
            return;
        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireContext()).getRetrofitAPI();
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("constituencyId", userInfo.constituencyId);
        jsonObject.addProperty("departmentId", departmentId);
        jsonObject.addProperty("deleteFlag", "N");
        jsonObject.addProperty("year", Integer.parseInt(binding.yearsSpinner.getSelectedItem().toString()));
        Utils.showProgessBar(requireContext());

        retrofitAPI.viewDepartmentAndYearDevelopment(jsonObject).enqueue(new Callback<VDevelopmentResponse>() {
            @Override
            public void onResponse(Call<VDevelopmentResponse> call, Response<VDevelopmentResponse> response) {
                try {
                    Utils.hideProgessBar();
                    Log.d(TAG, "onResponse: " + response.body());
                    if (response.body().constituencyDepartmentsList != null && response.body().constituencyDepartmentsList.size() > 0) {
                        binding.constituencyDevList.setAdapter(new ViewConstituencyDevAdapter(requireActivity(), response.body().constituencyDepartmentsList));
                        binding.noDataFoundTxt.setVisibility(View.GONE);
                    } else {
                        binding.constituencyDevList.setVisibility(View.GONE);
                        binding.noDataFoundTxt.setText(getString(R.string.no_constituency_department_data));
                        binding.noDataFoundTxt.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<VDevelopmentResponse> call, Throwable t) {

            }
        });

    }
}
