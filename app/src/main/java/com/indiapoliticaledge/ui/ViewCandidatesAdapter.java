package com.indiapoliticaledge.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.responsemodel.CandidatesList;
import com.indiapoliticaledge.ui.fragment.VoterProfileFragment;

import java.util.ArrayList;

public class ViewCandidatesAdapter extends RecyclerView.Adapter<ViewCandidatesAdapter.ViewMLAHolder> {

    private final Activity activity;
    private final ArrayList<CandidatesList> usersList;
    private UserInfo userInfo;

    public ViewCandidatesAdapter(Activity activity, ArrayList<CandidatesList> usersList, UserInfo userInfo) {
        this.activity = activity;
        this.usersList = usersList;
        this.userInfo = userInfo;
    }


    @NonNull
    @Override
    public ViewMLAHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewMLAHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_candidate_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMLAHolder holder, int position) {
        holder.bind(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewMLAHolder extends RecyclerView.ViewHolder {
        TextView mla_name, constituency_name_txt, party_name_txt, village_name_txt, mandal_name_txt, mobile_number_txt, date_of_birth_txt;
        ImageView party_icon, profile_image, edit_icon;

        public ViewMLAHolder(@NonNull View itemView) {
            super(itemView);
            mla_name = itemView.findViewById(R.id.mla_name);
            party_name_txt = itemView.findViewById(R.id.party_name_txt);
            date_of_birth_txt = itemView.findViewById(R.id.date_of_birth_txt);
            profile_image = itemView.findViewById(R.id.profile_image);
            party_icon = itemView.findViewById(R.id.party_icon);
            edit_icon = itemView.findViewById(R.id.edit_icon);
            constituency_name_txt = itemView.findViewById(R.id.constituency_name_txt);
            village_name_txt = itemView.findViewById(R.id.village_name_txt);
            mandal_name_txt = itemView.findViewById(R.id.mandal_name_txt);
            mobile_number_txt = itemView.findViewById(R.id.mobile_number_txt);
            mla_name.setOnClickListener(v -> {
                navigateToUpdateVoter(usersList.get(getBindingAdapterPosition()));
            });
            profile_image.setOnClickListener(v -> {
                navigateToUpdateVoter(usersList.get(getBindingAdapterPosition()));
            });
            party_icon.setOnClickListener(v -> {
                navigateToUpdateVoter(usersList.get(getBindingAdapterPosition()));
            });
            constituency_name_txt.setOnClickListener(v -> {
                navigateToUpdateVoter(usersList.get(getBindingAdapterPosition()));
            });
            edit_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToUpdateVoter(usersList.get(getBindingAdapterPosition()));
                }
            });
        }

        private void navigateToUpdateVoter(CandidatesList candidatesList) {
            VoterProfileFragment voterProfileFragment = new VoterProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("candidatesList", candidatesList);
            voterProfileFragment.setArguments(bundle);
            ((MLAInfoDrawerScreen) activity).updateFragment(voterProfileFragment);
        }

        public void bind(CandidatesList candidatesList) {
            mla_name.setText(candidatesList.firstName + " " + candidatesList.lastName);
            constituency_name_txt.setText(candidatesList.mobileNumber);
            if (candidatesList.age != 0) {
                party_name_txt.setText(activity.getString(R.string.age) + " : " + candidatesList.age);
            } else {
                party_name_txt.setText(activity.getString(R.string.age));
            }

            if (candidatesList.dateOfBirth != null) {
                date_of_birth_txt.setText(activity.getString(R.string.date_of_birth) + " : " + candidatesList.dateOfBirth);
            } else {
                date_of_birth_txt.setText(activity.getString(R.string.date_of_birth));
            }

            if (candidatesList.villageName != null) {
                village_name_txt.setText(activity.getString(R.string.village_name) + " : " + candidatesList.villageName);
            } else {
                village_name_txt.setText("Village :  ");
            }

            if (candidatesList.mandalName != null) {
                mandal_name_txt.setText("Mandal : " + candidatesList.mandalName);
            } else {
                mandal_name_txt.setText("Mandal :  ");
            }

            if (candidatesList.voterIdNumber != null) {
                mobile_number_txt.setText(activity.getString(R.string.voter_id) + " : " + candidatesList.voterIdNumber);
            }


        }
    }
}
