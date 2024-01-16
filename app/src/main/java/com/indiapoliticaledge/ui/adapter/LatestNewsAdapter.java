package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.SINGLE_LATEST_NEWS;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemLatestNewsBinding;
import com.indiapoliticaledge.network.responsemodel.NewsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.admin.AddUpdateLatestNewsFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsHolder> {
    private final Activity activity;
    private final ArrayList<NewsList> newsList;
    private String userInfo;

    public LatestNewsAdapter(Activity activity, ArrayList<NewsList> newsList, String userInfo) {
        this.activity = activity;
        this.newsList = newsList;
        this.userInfo = userInfo;
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

            binding.editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.USER_INFO, userInfo);
                    bundle.putSerializable(SINGLE_LATEST_NEWS, newsList.get(getAdapterPosition()));
                    AddUpdateLatestNewsFragment addUpdateLatestNewsFragment = new AddUpdateLatestNewsFragment();
                    addUpdateLatestNewsFragment.setArguments(bundle);
                    ((MLAInfoDrawerScreen) activity).updateFragment(addUpdateLatestNewsFragment);
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
