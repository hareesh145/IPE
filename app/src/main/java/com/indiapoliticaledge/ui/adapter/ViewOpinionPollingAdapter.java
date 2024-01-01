package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemOpinionPollingBinding;
import com.indiapoliticaledge.network.responsemodel.UserOpinionsList;

import java.util.ArrayList;

public class ViewOpinionPollingAdapter extends RecyclerView.Adapter<ViewOpinionPollingAdapter.OpinionHolder> {

    private Activity activity;
    private ArrayList<UserOpinionsList> userOpinionsList;

    public ViewOpinionPollingAdapter(Activity activity, ArrayList<UserOpinionsList> userOpinionsList) {
        this.activity = activity;
        this.userOpinionsList = userOpinionsList;
    }

    @NonNull
    @Override
    public OpinionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OpinionHolder(ItemOpinionPollingBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OpinionHolder holder, int position) {
        holder.bind(userOpinionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return userOpinionsList.size();
    }

    class OpinionHolder extends RecyclerView.ViewHolder {

        private ItemOpinionPollingBinding binding;

        public OpinionHolder(@NonNull ItemOpinionPollingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserOpinionsList userOpinionsList) {
            binding.mlaName.setText(userOpinionsList.opinionMessage);
            binding.donationAmount.setText("Mandal : " + userOpinionsList.mandalName);
            binding.villageName.setText("Village : " + userOpinionsList.villageName);
            binding.donationDate.setText("Create Date : " + userOpinionsList.createdDate.split("\\s")[0]);
        }
    }
}
