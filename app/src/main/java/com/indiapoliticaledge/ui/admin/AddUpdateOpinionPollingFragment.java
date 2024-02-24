package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.SINGLE_OPINION_POLLING;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddOpinionPollingLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.responsemodel.SignInResponseModel;
import com.indiapoliticaledge.network.responsemodel.UserOpinionsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.SharedPref;

public class AddUpdateOpinionPollingFragment extends Fragment {

    AddOpinionPollingLayoutBinding binding;

    UserOpinionsList userOpinionsList;
    UserInfo userInfo;
    SignInResponseModel signInResponseModel;

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

        signInResponseModel = new Gson().fromJson(SharedPref.getmSharedPrefInstance(requireContext())
                .getString(Constants.LOGIN_RESPONSE), SignInResponseModel.class);


        if (userOpinionsList != null) {
            bindUserOpinionsList();
        }else {
            binding.constituencyNameTxt.setText(getString(R.string.constituency_name) + " : " + signInResponseModel.constituencyName);
            binding.districtNameTxt.setText(getString(R.string.district) + " : " + signInResponseModel.constituencyDistrict);
            binding.stateNameTxt.setText(getString(R.string.state) + " : " + signInResponseModel.constituencyState);
        }
    }

    private void bindUserOpinionsList() {
        binding.constituencyNameTxt.setText(getString(R.string.constituency_name) + " : " + userOpinionsList.villageName);
        binding.districtNameTxt.setText(getString(R.string.district) + " : " + userOpinionsList.mandalName);
        binding.opinionCandidatePollingTxt.setText(userOpinionsList.opinionMessage);
        if (userInfo != null)
            binding.stateNameTxt.setText(getString(R.string.state) + " : " + signInResponseModel.constituencyState);
    }
}
