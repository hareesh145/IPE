package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ItemBoothLevelManagementBinding;
import com.indiapoliticaledge.model.BoothlevelMgmtInfo;

import java.util.ArrayList;

public class BoothLevelAdapter extends RecyclerView.Adapter<BoothLevelAdapter.BoothLevelHolder> {

    private final Activity activity;
    private final ArrayList<BoothlevelMgmtInfo> boothlevelMgmtInfos;

    public BoothLevelAdapter(Activity activity, ArrayList<BoothlevelMgmtInfo> boothlevelMgmtInfos) {
        this.activity = activity;
        this.boothlevelMgmtInfos = boothlevelMgmtInfos;
    }

    @NonNull
    @Override
    public BoothLevelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoothLevelHolder(ItemBoothLevelManagementBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoothLevelHolder holder, int position) {
        holder.bind(boothlevelMgmtInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return boothlevelMgmtInfos.size();
    }

    class BoothLevelHolder extends RecyclerView.ViewHolder {

        private ItemBoothLevelManagementBinding binding;

        public BoothLevelHolder(@NonNull ItemBoothLevelManagementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BoothlevelMgmtInfo boothlevelMgmtInfo) {
            binding.mlaName.setText(activity.getString(R.string.group_name) + " : " + boothlevelMgmtInfo.groupName);
            binding.donationAmount.setText(activity.getString(R.string.mandal_name) + " : " + boothlevelMgmtInfo.mandalName);
            binding.villageName.setText(activity.getString(R.string.village_name) + " : " + boothlevelMgmtInfo.villageName);
            if (boothlevelMgmtInfo.createdDate != null) {
                binding.donationDate.setText(activity.getString(R.string.created_date) + " : " + boothlevelMgmtInfo.createdDate.split("\\s")[0]);
            }
        }
    }
}
