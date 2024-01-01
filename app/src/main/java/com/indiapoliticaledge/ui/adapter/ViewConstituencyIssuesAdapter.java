package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemConstituencyIssuesBinding;
import com.indiapoliticaledge.network.responsemodel.IssuesList;

import java.util.ArrayList;

public class ViewConstituencyIssuesAdapter extends RecyclerView.Adapter<ViewConstituencyIssuesAdapter.ConstituencyIssuesHolder> {

    private final Activity activity;
    private final ArrayList<IssuesList> issuesLists;

    public ViewConstituencyIssuesAdapter(Activity activity, ArrayList<IssuesList> issuesLists) {
        this.activity = activity;
        this.issuesLists = issuesLists;
    }

    @NonNull
    @Override
    public ConstituencyIssuesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConstituencyIssuesHolder(ItemConstituencyIssuesBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConstituencyIssuesHolder holder, int position) {
        holder.bind(issuesLists.get(position));
    }

    @Override
    public int getItemCount() {
        return issuesLists.size();
    }

    class ConstituencyIssuesHolder extends RecyclerView.ViewHolder {

        private com.indiapoliticaledge.databinding.ItemConstituencyIssuesBinding binding;

        public ConstituencyIssuesHolder(@NonNull ItemConstituencyIssuesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(IssuesList issuesList) {
            binding.issuesTxt.setText("Issue : " + issuesList.describeIssue);
            binding.mandalTxt.setText("Mandal : " + issuesList.mandalName);
            binding.villageTxt.setText("Village : " + issuesList.villageName);
            binding.createdDateTxt.setText("Create Date : " + issuesList.createdDate);
            binding.sarpanchTxt.setText("Sarpanch Name : " + issuesList.sarpanchName);
        }
    }
}
