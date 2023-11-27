package com.indiapoliticaledge.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;

import java.util.ArrayList;

public class ViewCandidatesAdapter extends RecyclerView.Adapter<ViewCandidatesAdapter.ViewMLAHolder> {

    private final Activity activity;
    private final ArrayList<UserInfo> usersList;

    public ViewCandidatesAdapter(Activity activity, ArrayList<UserInfo> usersList) {
        this.activity = activity;
        this.usersList = usersList;
    }


    @NonNull
    @Override
    public ViewMLAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewMLAHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_candidate_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMLAHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewMLAHolder extends RecyclerView.ViewHolder {

        public ViewMLAHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.mla_name).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.profile_image).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.party_icon).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.constituency_name_txt).setOnClickListener(v -> {
                Intent intent = new Intent(activity, ViewMLAInfoScreen.class);
                activity.startActivity(intent);
            });
            itemView.findViewById(R.id.edit_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, UpdateMLAScreen.class);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
