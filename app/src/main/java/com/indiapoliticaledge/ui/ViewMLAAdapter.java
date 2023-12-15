package com.indiapoliticaledge.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.fragment.ConstituencyMapFragment;

import java.util.ArrayList;

public class ViewMLAAdapter extends RecyclerView.Adapter<ViewMLAAdapter.ViewMLAHolder> {

    private final Activity activity;
    private final ArrayList<UserInfo> usersList;

    public ViewMLAAdapter(Activity activity, ArrayList<UserInfo> usersList) {
        this.activity = activity;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewMLAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewMLAHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mla_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMLAHolder holder, int position) {
        UserInfo userInfo = usersList.get(position);
        if (userInfo != null) {
            holder.constituency_name_txt.setText(userInfo.getConstituency().constituencyName);
            holder.mla_name.setText(userInfo.firstName + " " + userInfo.lastName);
            if (userInfo.userConstituencies != null) {
                if (userInfo.userConstituencies.startDate != null) {
                    holder.start_date_value_txt.setText(userInfo.userConstituencies.startDate.split("\\s")[0]);
                }
                if (userInfo.userConstituencies.endDate != null) {
                    holder.end_date_value_txt.setText(userInfo.userConstituencies.endDate.split("\\s")[0]);
                }
            } else {
                if (userInfo.startDate != null) {
                    holder.start_date_value_txt.setText(userInfo.startDate.split("\\s")[0]);
                }
                if (userInfo.endDate != null) {
                    holder.end_date_value_txt.setText(userInfo.endDate.split("\\s")[0]);
                }
            }
            Glide.with(activity).load(userInfo.getProfilePhotoUrl()).placeholder(R.drawable.ic_user_logo).into(holder.profile_image);

            Glide.with(activity).load(userInfo.getPartyLogo()).placeholder(R.mipmap.ic_launcher).into(holder.party_icon);

        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewMLAHolder extends RecyclerView.ViewHolder {
        TextView mla_name, constituency_name_txt, start_date_value_txt, end_date_value_txt;
        ImageView dashboard_icon, profile_image,party_icon;

        public ViewMLAHolder(@NonNull View itemView) {
            super(itemView);
            mla_name = itemView.findViewById(R.id.mla_name);
            constituency_name_txt = itemView.findViewById(R.id.constituency_name_txt);
            start_date_value_txt = itemView.findViewById(R.id.start_date_value_txt);
            end_date_value_txt = itemView.findViewById(R.id.end_date_value_txt);
            dashboard_icon = itemView.findViewById(R.id.dashboard_icon);
            profile_image = itemView.findViewById(R.id.profile_image);
            party_icon = itemView.findViewById(R.id.party_icon);

            itemView.findViewById(R.id.mla_name).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                Log.d("TAG", "userInfo " + userInfo);
                intent.putExtra("user_id", userInfo.getUserId());
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.profile_image).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                Log.d("TAG", "userInfo " + userInfo);
                intent.putExtra("user_id", userInfo.getUserId());
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.party_icon).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                Log.d("TAG", "userInfo " + userInfo);
                intent.putExtra("user_id", userInfo.getUserId());
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.constituency_name_txt).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                Log.d("TAG", "userInfo " + userInfo);
                intent.putExtra("user_id", userInfo.getUserId());
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.edit_icon).setOnClickListener(v -> {
                Intent intent = new Intent(activity, UpdateMLAScreen.class);
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                Log.d("TAG", "userInfo " + userInfo);
                intent.putExtra("user_id", userInfo.getUserId());
                activity.startActivity(intent);
            });

            itemView.findViewById(R.id.delete_icon).setOnClickListener(v -> {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                builder1.setMessage("Are you sure you want to delete?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        (dialog, id) -> dialog.cancel());

                builder1.setNegativeButton(
                        "No",
                        (dialog, id) -> dialog.cancel());

                AlertDialog alert11 = builder1.create();
                alert11.show();
            });

            dashboard_icon.setOnClickListener(v -> {
                UserInfo userInfo = usersList.get(getAbsoluteAdapterPosition());
                ((SuperAdminScreen) activity).createFragment(new ConstituencyMapFragment(), new Gson().toJson(userInfo));
            });
        }
    }
}
