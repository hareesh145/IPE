package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.databinding.BoothPoolManagementLayoutBinding;
import com.indiapoliticaledge.databinding.PoolManagementLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.utils.Constants;

public class ConstituencyPoolManagementFragment extends Fragment {

    PoolManagementLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PoolManagementLayoutBinding.inflate(inflater, container, false);
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
                    AddPollCenterFragment addBoothPoolManagement = new AddPollCenterFragment();
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(addBoothPoolManagement, bundle.getString(Constants.USER_INFO));
                }
            }
        });
    }
}
