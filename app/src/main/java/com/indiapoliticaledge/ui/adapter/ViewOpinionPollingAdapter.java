package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.SINGLE_OPINION_POLLING;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemOpinionPollingBinding;
import com.indiapoliticaledge.network.responsemodel.UserOpinionsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.admin.AddUpdateOpinionPollingFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class ViewOpinionPollingAdapter extends RecyclerView.Adapter<ViewOpinionPollingAdapter.OpinionHolder> {

    private Activity activity;
    private ArrayList<UserOpinionsList> userOpinionsList;
    private String userInfo;

    public ViewOpinionPollingAdapter(Activity activity, ArrayList<UserOpinionsList> userOpinionsList, String userInfo) {
        this.activity = activity;
        this.userOpinionsList = userOpinionsList;
        this.userInfo = userInfo;
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

            binding.editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.USER_INFO, userInfo);
                    bundle.putSerializable(SINGLE_OPINION_POLLING, userOpinionsList.get(getAdapterPosition()));
                    AddUpdateOpinionPollingFragment addUpdateOpinionPollingFragment = new AddUpdateOpinionPollingFragment();
                    addUpdateOpinionPollingFragment.setArguments(bundle);
                    ((MLAInfoDrawerScreen) activity).updateFragment(addUpdateOpinionPollingFragment);
                }
            });

            binding.deleteIcon.setOnClickListener(v -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                builder1.setMessage("Are you sure you want to delete?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();

                });
                builder1.setNegativeButton(
                        "No",
                        (dialog, id) -> dialog.cancel());

                AlertDialog alert11 = builder1.create();
                alert11.show();
            });
        }

        public void bind(UserOpinionsList userOpinionsList) {
            binding.mlaName.setText(userOpinionsList.opinionMessage);
            binding.donationAmount.setText("Mandal : " + userOpinionsList.mandalName);
            binding.villageName.setText("Village : " + userOpinionsList.villageName);
            binding.donationDate.setText("Create Date : " + userOpinionsList.createdDate.split("\\s")[0]);
        }
    }
}
