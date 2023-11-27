package com.indiapoliticaledge.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {


    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ImagesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ImagesViewHolder extends RecyclerView.ViewHolder {

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
