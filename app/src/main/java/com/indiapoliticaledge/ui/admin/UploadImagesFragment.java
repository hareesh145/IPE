package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.UploadImagesLayouyBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.CandidateHomeScreen;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.fragment.CandidateAVFragment;
import com.indiapoliticaledge.utils.Constants;

public class UploadImagesFragment extends Fragment {
    UploadImagesLayouyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UploadImagesLayouyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        binding.uploadAvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requireActivity() instanceof MLAInfoDrawerScreen) {
                    ((MLAInfoDrawerScreen) requireActivity()).createFragment(new CandidateAVFragment(), jsonObjectUser);
                    ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.upload_av));
                }else if (requireActivity() instanceof CandidateHomeScreen) {
                    ((CandidateHomeScreen) requireActivity()).createFragment(new CandidateAVFragment(), jsonObjectUser);
                    ((CandidateHomeScreen) requireActivity()).setTitleText(getString(R.string.upload_av));
                }
            }
        });
    }
}
