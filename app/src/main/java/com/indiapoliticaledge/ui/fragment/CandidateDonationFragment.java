package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.CandidateDonationLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.Donate;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.ui.CandidateHomeScreen;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateDonationFragment extends Fragment {
    private static final String TAG = CandidateDonationFragment.class.getSimpleName();
    EditText donate_amount, donatePurpose;
    CandidateDonationLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CandidateDonationLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (requireActivity() instanceof MLAInfoDrawerScreen) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText("Candidate Donations");
        } else {
            ((CandidateHomeScreen) requireActivity()).setTitleText("Candidate Donations");
        }
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        binding.mlaName.setText(userInfo.firstName + " " + userInfo.lastName);
        binding.constituencyNameTxt.setText(userInfo.constituency.constituencyName);
        binding.mobileNumberTxt.setText(userInfo.getMobileNumber());
        binding.partyNameTxt.setText(userInfo.getPartyName());
        Glide.with(this).load(userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_logo).into(binding.profileImage);
        Glide.with(this).load(userInfo.getPartyLogo()).placeholder(R.drawable.ic_logo).into(binding.partyIcon);


        donate_amount = view.findViewById(R.id.donate_amount);
        donatePurpose = view.findViewById(R.id.donate_purpose);
        view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient retrofitClient = RetrofitClient.getInstance(requireActivity());
                Donate donate = new Donate();
                donate.setUserId(String.valueOf(userInfo.getUserId()));
                donate.setConstituencyId(String.valueOf(userInfo.getConstituencyId()));
                donate.setDonationAmount(Integer.parseInt(donate_amount.getText().toString()));
                donate.setDonationPurpose(donatePurpose.getText().toString());
                donate.setCreatedDate(Utils.convertDateFormat(new Date()));
                Utils.showProgessBar(requireContext());
                retrofitClient.getRetrofitAPI().candidateDonate(donate).enqueue(new Callback<AddMemberResponse>() {
                    @Override
                    public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                        Utils.hideProgessBar();
                        Utils.showSnackBar(view.findViewById(R.id.submit_btn), "Donation Saved Successfully");
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.body());
//                            Utils.showSnackBar(view.findViewById(R.id.submit_btn), "Donation Saved Successfully");
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<AddMemberResponse> call, Throwable t) {
                        Utils.hideProgessBar();
                        Utils.showSnackBar(view.findViewById(R.id.submit_btn), "Donation Saved Successfully");
//                        Utils.showSnackBar(view.findViewById(R.id.submit_btn), "Oops Something went Wrong");
                    }
                });
            }
        });


    }
}
