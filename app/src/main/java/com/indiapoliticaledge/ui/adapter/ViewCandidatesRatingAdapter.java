package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemCandidatesRatingBinding;
import com.indiapoliticaledge.network.responsemodel.UserRatingsList;

import java.util.ArrayList;

public class ViewCandidatesRatingAdapter extends RecyclerView.Adapter<ViewCandidatesRatingAdapter.CandidateRatingHolder> {
    private final Activity activity;
    private final ArrayList<UserRatingsList> userRatingsList;

    public ViewCandidatesRatingAdapter(Activity activity, ArrayList<UserRatingsList> userRatingsList) {
        this.activity = activity;
        this.userRatingsList = userRatingsList;
    }

    @NonNull
    @Override
    public CandidateRatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CandidateRatingHolder(ItemCandidatesRatingBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateRatingHolder holder, int position) {
        holder.bind(userRatingsList.get(position));
    }

    @Override
    public int getItemCount() {
        return userRatingsList.size();
    }


    class CandidateRatingHolder extends RecyclerView.ViewHolder {

        private ItemCandidatesRatingBinding binding;

        public CandidateRatingHolder(@NonNull ItemCandidatesRatingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserRatingsList userRatingsList) {
            binding.ratingBar.setRating(userRatingsList.rating);
            binding.mlaName.setText(userRatingsList.candidates.firstName + " " + userRatingsList.candidates.lastName);
            binding.dateText.setText("Date : " + userRatingsList.createdDate);
            if (userRatingsList.candidates.villageName != null) {
                binding.villageName.setText("Village : " + userRatingsList.candidates.villageName);
            } else {
                binding.villageName.setText("Village : ");
            }
            if (userRatingsList.candidates.voterIdNumber != null) {
                binding.voterIdTxt.setText("Voter ID : " + userRatingsList.candidates.voterIdNumber);
            } else {
                binding.voterIdTxt.setText("Voter ID : ");
            }
            if (userRatingsList.candidates.mandalName != null) {
                binding.mandalName.setText("Mandal : " + userRatingsList.candidates.mandalName);
            } else {
                binding.mandalName.setText("Mandal : ");
            }
            binding.mobileNumberTxt.setText("Mobile Number : " + userRatingsList.candidates.mobileNumber);
        }
    }
}
