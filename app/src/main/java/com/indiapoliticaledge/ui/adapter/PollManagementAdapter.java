package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.POLL_MANEGEMENT;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ItemBoothLevelManagementBinding;
import com.indiapoliticaledge.model.PollManagementList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.ListOfVotersScreen;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class PollManagementAdapter extends RecyclerView.Adapter<PollManagementAdapter.BoothLevelHolder> {

    private final Activity activity;
    private final ArrayList<PollManagementList> pollManagementLists;
    private UserInfo userInfo;

    public PollManagementAdapter(Activity activity, ArrayList<PollManagementList> pollManagementLists, UserInfo userInfo) {
        this.activity = activity;
        this.pollManagementLists = pollManagementLists;
        this.userInfo = userInfo;
    }

    @NonNull
    @Override
    public BoothLevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoothLevelHolder(ItemBoothLevelManagementBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoothLevelHolder holder, int position) {
        holder.bind(pollManagementLists.get(position));
    }

    @Override
    public int getItemCount() {
        return pollManagementLists.size();
    }

    class BoothLevelHolder extends RecyclerView.ViewHolder {

        private ItemBoothLevelManagementBinding binding;

        public BoothLevelHolder(@NonNull ItemBoothLevelManagementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ListOfVotersScreen.class);
                    intent.putExtra(Constants.USER_INFO, new Gson().toJson(userInfo));
                    intent.putExtra(POLL_MANEGEMENT, pollManagementLists.get(getBindingAdapterPosition()));
                    activity.startActivity(intent);
                }
            });
        }

        public void bind(PollManagementList boothlevelMgmtInfo) {
            binding.mlaName.setText(activity.getString(R.string.poll_center_name) + " : " + boothlevelMgmtInfo.pollingCenterName);
            binding.donationAmount.setText(activity.getString(R.string.poll_center_id) + " : " + boothlevelMgmtInfo.pollingCenterId);
            binding.villageName.setText(activity.getString(R.string.village_name) + " : " + boothlevelMgmtInfo.villageName);
            binding.donationDate.setText(activity.getString(R.string.mandal_name) + " : " + boothlevelMgmtInfo.mandalName);
        }
    }
}
