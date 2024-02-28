package com.indiapoliticaledge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.VoterProfileLayoutBinding;

public class VoterProfileFragment extends Fragment {

    VoterProfileLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = VoterProfileLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_new_candidate));

        saveVoterInfo();
    }

    private void saveVoterInfo() {
        if (binding.firstNameEt.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.first_name_required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.lastNameEt.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.last_name_required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.mobile_number_required), Toast.LENGTH_SHORT).show();
            return;
        }

        if (binding.partyEdit.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.age_required), Toast.LENGTH_SHORT).show();
            return;
        }

        if (binding.voterIdNumber.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.voter_id_required), Toast.LENGTH_SHORT).show();
            return;
        }

        if (binding.voterIdNumber.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.voter_id_required), Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
