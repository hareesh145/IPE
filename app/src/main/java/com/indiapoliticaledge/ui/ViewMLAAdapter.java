package com.indiapoliticaledge.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;

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
        }
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewMLAHolder extends RecyclerView.ViewHolder {
        TextView mla_name, constituency_name_txt, start_date_value_txt, end_date_value_txt;

        public ViewMLAHolder(@NonNull View itemView) {
            super(itemView);

            mla_name = itemView.findViewById(R.id.mla_name);
            constituency_name_txt = itemView.findViewById(R.id.constituency_name_txt);
            start_date_value_txt = itemView.findViewById(R.id.start_date_value_txt);
            end_date_value_txt = itemView.findViewById(R.id.end_date_value_txt);

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
        }
    }
}
