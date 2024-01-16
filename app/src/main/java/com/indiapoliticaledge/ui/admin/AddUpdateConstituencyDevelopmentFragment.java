package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.SINGLE_CONSTITUENCY_DEV;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddConstituencyDevLayoutBinding;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;

public class AddUpdateConstituencyDevelopmentFragment extends Fragment {
    AddConstituencyDevLayoutBinding binding;
    ConstituencyDepartmentsList constituencyDepartmentsList;

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
        if (bundle.containsKey(SINGLE_CONSTITUENCY_DEV)) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.update_constituency_development));
            constituencyDepartmentsList = (ConstituencyDepartmentsList) bundle.get(SINGLE_CONSTITUENCY_DEV);
        } else {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_constituency_development));
        }

        if (constituencyDepartmentsList != null) {
            bindConstituencyDepartmentsList();
        }

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
