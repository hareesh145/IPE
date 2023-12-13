package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.ui.CandidateHomeScreen;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;

public class CandidateAVFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upload_av_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (requireActivity() instanceof MLAInfoDrawerScreen) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText("Candidate Audio/Video");
        } else if (requireActivity() instanceof CandidateHomeScreen) {
            ((CandidateHomeScreen) requireActivity()).setTitleText("Candidate Audio/Video");
        }
    }
}
