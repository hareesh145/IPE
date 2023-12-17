package com.indiapoliticaledge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.databinding.ForgotPwdLayoutBinding;

public class ForgotPwdScreen extends Fragment {
    ForgotPwdLayoutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ForgotPwdLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.newPwdEdit.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.confirmPwdEdit.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(requireActivity(), "Password Sent to mobile number", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
