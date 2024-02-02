package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ItemVoterListBinding;
import com.indiapoliticaledge.model.VotersList;

import java.util.ArrayList;

public class VotersListAdapter extends RecyclerView.Adapter<VotersListAdapter.VoterListHolder> {

    private final Activity activity;
    private final ArrayList<VotersList> votersList;

    @NonNull
    @Override
    public VoterListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VoterListHolder(ItemVoterListBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VoterListHolder holder, int position) {
        holder.bind(votersList.get(position));
    }

    @Override
    public int getItemCount() {
        return votersList.size();
    }

    class VoterListHolder extends RecyclerView.ViewHolder {

        private com.indiapoliticaledge.databinding.ItemVoterListBinding binding;

        public VoterListHolder(@NonNull ItemVoterListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(VotersList votersList) {
            binding.mlaName.setText(activity.getString(R.string.name) + " : "
                    + votersList.firstName + " " + votersList.lastName);
            binding.donationAmount.setText(activity.getString(R.string.mobile_number)
                    + " : " + votersList.mobileNumber);
            binding.donationDate.setText(activity.getString(R.string.birth_place) + " : " + votersList.birthPlace);
            binding.villageName.setText(activity.getString(R.string.village_name) + " : " + votersList.villageName);
            binding.voterId.setText(activity.getString(R.string.voter_id) + " : " + votersList.voterIdNumber);
        }
    }

    public VotersListAdapter(Activity activity, ArrayList<VotersList> votersList) {
        this.activity = activity;
        this.votersList = votersList;
    }
}
