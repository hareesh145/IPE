package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemCandidateDonateBinding;
import com.indiapoliticaledge.network.responsemodel.CandidatesDonationsList;

import java.util.ArrayList;
import java.util.List;

public class ViewCandidateDonationAdapter extends RecyclerView.Adapter<ViewCandidateDonationAdapter.DonationHolder> {

    private final Activity activity;
    private final List<CandidatesDonationsList> candidatesLists;

    public ViewCandidateDonationAdapter(Activity activity, ArrayList<CandidatesDonationsList> candidatesLists) {
        this.activity = activity;
        this.candidatesLists = candidatesLists;
    }

    @NonNull
    @Override
    public DonationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewCandidateDonationAdapter.DonationHolder(ItemCandidateDonateBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonationHolder holder, int position) {
        holder.bind(candidatesLists.get(position));
    }

    @Override
    public int getItemCount() {
        return candidatesLists.size();
    }

    class DonationHolder extends RecyclerView.ViewHolder {
        ItemCandidateDonateBinding binding;

        public DonationHolder(@NonNull ItemCandidateDonateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CandidatesDonationsList candidatesDonationsList) {
            binding.mlaName.setText("Name : " + candidatesDonationsList.candidates.firstName + " " + candidatesDonationsList.candidates.lastName);
            binding.donationAmount.setText("Donation Amount : " + candidatesDonationsList.donationAmount);
            binding.donationPurpose.setText("Donation Purpose : " + candidatesDonationsList.donationPurpose);
            binding.donationDate.setText("Donated Date :  " + candidatesDonationsList.createdDate);
            binding.mobileNumberTxt.setText("Mobile Number : " + candidatesDonationsList.candidates.mobileNumber);
            if (candidatesDonationsList.candidates.villageName!=null) {
                binding.villageName.setText("Village : " + candidatesDonationsList.candidates.villageName);
                binding.villageName.setVisibility(View.VISIBLE);
            }else {
                binding.villageName.setVisibility(View.GONE);
            }
        }
    }
}
