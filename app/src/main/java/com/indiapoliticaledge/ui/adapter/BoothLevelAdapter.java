package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.BOOTH_LEVEL_MANEGEMENT;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ItemBoothLevelManagementBinding;
import com.indiapoliticaledge.model.BoothlevelMgmtInfo;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.ListOfVotersScreen;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class BoothLevelAdapter extends RecyclerView.Adapter<BoothLevelAdapter.BoothLevelHolder> {

    private final Activity activity;
    private final ArrayList<BoothlevelMgmtInfo> boothlevelMgmtInfos;
    private UserInfo userInfo;

    public BoothLevelAdapter(Activity activity, ArrayList<BoothlevelMgmtInfo> boothlevelMgmtInfos, UserInfo userInfo) {
        this.activity = activity;
        this.boothlevelMgmtInfos = boothlevelMgmtInfos;
        this.userInfo = userInfo;
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

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ListOfVotersScreen.class);
                    intent.putExtra(Constants.USER_INFO, new Gson().toJson(userInfo));
                    intent.putExtra(BOOTH_LEVEL_MANEGEMENT, boothlevelMgmtInfos.get(getBindingAdapterPosition()));
                    activity.startActivity(intent);
                }
            });
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
