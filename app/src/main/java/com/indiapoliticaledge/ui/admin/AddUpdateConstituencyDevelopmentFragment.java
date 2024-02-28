package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.DEPRTMENT_LIST;
import static com.indiapoliticaledge.utils.Constants.SINGLE_CONSTITUENCY_DEV;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddConstituencyDevLayoutBinding;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;
import com.indiapoliticaledge.model.DepartmentsList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.CDevelopmentRequest;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.adapter.CustomSpinnerAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUpdateConstituencyDevelopmentFragment extends Fragment {
    AddConstituencyDevLayoutBinding binding;
    ConstituencyDepartmentsList constituencyDepartmentsList;

    ArrayList<DepartmentsList> departmentsLists;
    ArrayList<String> yearsList = new ArrayList<>();
    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddConstituencyDevLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();

        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        if (bundle.containsKey(SINGLE_CONSTITUENCY_DEV)) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.update_constituency_development));
            constituencyDepartmentsList = (ConstituencyDepartmentsList) bundle.get(SINGLE_CONSTITUENCY_DEV);
        } else {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_constituency_development));
        }

        if (bundle.containsKey(DEPRTMENT_LIST)) {
            departmentsLists = (ArrayList<DepartmentsList>) bundle.getSerializable(
                    DEPRTMENT_LIST);
        }

        if (constituencyDepartmentsList != null) {
            bindConstituencyDepartmentsList();
        }

        if (departmentsLists != null) {
            if (departmentsLists.size() > 0) {
                DepartmentsList departmentsList = new DepartmentsList();
                departmentsList.departmentName = "" + getResources().getString(R.string.select_constituency_department);
                departmentsList.departmentId = -1;
                departmentsLists.add(0, departmentsList);
            }
            binding.constituencyDevSpinner.setAdapter(new CustomSpinnerAdapter(requireContext(), departmentsLists));
        }

        setYearsList();
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CDevelopmentRequest cDevelopmentRequest = new CDevelopmentRequest();
                    cDevelopmentRequest.constituencyId = userInfo.constituencyId;
                    cDevelopmentRequest.departmentId = ((DepartmentsList) binding.constituencyDevSpinner.getSelectedItem()).departmentId;
                    cDevelopmentRequest.year = Integer.parseInt(binding.yearsSpinner.getSelectedItem().toString());

                    cDevelopmentRequest.workDescription = binding.pastDevelopmentEdit.getText().toString();
                    cDevelopmentRequest.workLocation = binding.mandalVillageEt.getText().toString();
                    if (binding.donateAmount.getText().toString().isEmpty()) {
                        cDevelopmentRequest.workAmount = 0.0;
                    } else {
                        cDevelopmentRequest.workAmount = Double.parseDouble(binding.donateAmount.getText().toString());
                    }

                    cDevelopmentRequest.currentYear = Integer.parseInt(binding.newYearsSpinner.getSelectedItem().toString());


                    cDevelopmentRequest.currentWorkDescription = binding.currentDevelopmentEdit.getText().toString();
                    cDevelopmentRequest.currentWorkLocation = binding.newMandalVillageEt.getText().toString();
                    if (binding.newDonateAmount.getText().toString().isEmpty()) {
                        cDevelopmentRequest.currentWorkAmount = 0.0;
                    } else {
                        cDevelopmentRequest.currentWorkAmount = Double.parseDouble(binding.donateAmount.getText().toString());
                    }

                    Utils.showProgessBar(requireContext());
                    RetrofitClient.getInstance(requireContext()).getRetrofitAPI().addConstituencyDevelopment(cDevelopmentRequest).enqueue(new Callback<AddMemberResponse>() {
                        @Override
                        public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                            Utils.hideProgessBar();
                            Toast.makeText(requireActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<AddMemberResponse> call, Throwable t) {
                            Utils.hideProgessBar();
                        }
                    });
                } catch (Exception e) {

                }

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

        binding.newYearsSpinner.setAdapter(stringArrayAdapter);
        binding.newYearsSpinner.setSelection(0);
    }

    private boolean validateDetails() {

        return false;
    }

    private void bindConstituencyDepartmentsList() {
        binding.donateAmount.setText("" + constituencyDepartmentsList.workAmount);
        binding.newDonateAmount.setText("" + constituencyDepartmentsList.currentWorkAmount);
        binding.pastDevelopmentEdit.setText(constituencyDepartmentsList.workDescription);
        binding.currentDevelopmentEdit.setText(constituencyDepartmentsList.currentWorkDescription);
        binding.mandalVillageEt.setText(constituencyDepartmentsList.workLocation);
        binding.newMandalVillageEt.setText(constituencyDepartmentsList.currentWorkLocation);
    }
}
