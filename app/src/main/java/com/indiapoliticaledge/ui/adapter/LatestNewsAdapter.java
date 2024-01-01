package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemLatestNewsBinding;
import com.indiapoliticaledge.network.responsemodel.NewsList;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsHolder> {
    private final Activity activity;
    private final ArrayList<NewsList> newsList;

    public LatestNewsAdapter(Activity activity, ArrayList<NewsList> newsList) {
        this.activity = activity;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public LatestNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatestNewsHolder(ItemLatestNewsBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LatestNewsHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class LatestNewsHolder extends RecyclerView.ViewHolder {

        private com.indiapoliticaledge.databinding.ItemLatestNewsBinding binding;

        public LatestNewsHolder(@NonNull ItemLatestNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(NewsList newsList) {
            Log.d("TAG", "bind: " + newsList);
            if (newsList.users != null) {
                binding.mlaName.setText("By " + newsList.users.firstName + " " + newsList.users.lastName);
            } else if (newsList.candidates != null) {
                binding.mlaName.setText("By " + newsList.candidates.firstName + " " + newsList.candidates.lastName);
            }
            binding.createdDateTxt.setText(newsList.createdDate.split("\\s")[0]);
            binding.newTitleTxt.setText(newsList.newsTitle);
            binding.newDescTxt.setText(newsList.newsDescription);
        }
    }
}
