package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.SINGLE_OPINION_POLLING;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddOpinionPollingLayoutBinding;
import com.indiapoliticaledge.network.responsemodel.UserOpinionsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;

public class AddUpdateOpinionPollingFragment extends Fragment {

    AddOpinionPollingLayoutBinding binding;

    UserOpinionsList userOpinionsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddOpinionPollingLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle.containsKey(SINGLE_OPINION_POLLING)) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.update_constituency_opinion));
            userOpinionsList = (UserOpinionsList) bundle.get(SINGLE_OPINION_POLLING);
        } else {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_constituency_opinion));
        }

        if (userOpinionsList != null) {
            bindUserOpinionsList();
        }
    }

    private void bindUserOpinionsList() {
        binding.constituencyNameTxt.setText(getString(R.string.constituency_name) + " : " + userOpinionsList.villageName);
        binding.districtNameTxt.setText(getString(R.string.district) + " : " + userOpinionsList.mandalName);
        binding.opinionCandidatePollingTxt.setText(userOpinionsList.opinionMessage);
    }
}
