package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemSplashImageBinding;

import java.util.ArrayList;

public class SplashImageAdapter extends RecyclerView.Adapter<SplashImageAdapter.ImageHolder> {

    private final Activity activity;
    ArrayList<Integer> resources;

    public SplashImageAdapter(Activity activity, ArrayList<Integer> resources) {
        this.activity = activity;
        this.resources = resources;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ItemSplashImageBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.binding.image.setImageResource(resources.get(position));
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        private ItemSplashImageBinding binding;

        public ImageHolder(@NonNull ItemSplashImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
