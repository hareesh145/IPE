package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.ItemImageLayoutBinding;
import com.indiapoliticaledge.network.responsemodel.ConstituencyImage;

import java.util.List;

public class ConstituencyImagesAdapter extends RecyclerView.Adapter<ConstituencyImagesAdapter.ImageHolder> {

    private final Activity activity;
    private final List<ConstituencyImage> myImages;

    public ConstituencyImagesAdapter(Activity activity, List<ConstituencyImage> myImages) {
        this.activity = activity;
        this.myImages = myImages;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ItemImageLayoutBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.bind(myImages.get(position));
    }

    @Override
    public int getItemCount() {
        return myImages.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        private ItemImageLayoutBinding binding;

        public ImageHolder(@NonNull ItemImageLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ConstituencyImage myImage) {
            Glide.with(activity.getApplicationContext()).load(myImage.filePath).placeholder(R.mipmap.ic_launcher).into(binding.image);
        }
    }
}
