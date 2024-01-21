package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.indiapoliticaledge.databinding.ItemVideoLayoutBinding;
import com.indiapoliticaledge.network.responsemodel.ConstituencyAV;

import java.util.List;

public class ConstituencyAVAdapter extends RecyclerView.Adapter<ConstituencyAVAdapter.ImageHolder> {

    private final Activity activity;
    private final List<ConstituencyAV> myImages;

    public ConstituencyAVAdapter(Activity activity, List<ConstituencyAV> myImages) {
        this.activity = activity;
        this.myImages = myImages;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ItemVideoLayoutBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.bind(myImages.get(position));
    }

    @Override
    public int getItemCount() {
        return myImages.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder implements OnPreparedListener {

        private ItemVideoLayoutBinding binding;

        public ImageHolder(@NonNull ItemVideoLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ConstituencyAV myImage) {

            binding.image.setOnPreparedListener(this);
            // For now we just picked an arbitrary item to play
            binding.image.setMedia(Uri.parse(myImage.filePath));
        }

        @Override
        public void onPrepared() {

        }
    }
}
