package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.network.responsemodel.TestimonialsList;

import java.util.ArrayList;

public class TestimonialsAdapter extends RecyclerView.Adapter<TestimonialsAdapter.TestimonialHolder> {

    private final Activity activity;
    private final ArrayList<TestimonialsList> testimonialsLists;

    public TestimonialsAdapter(Activity activity, ArrayList<TestimonialsList> testimonialsLists) {
        this.activity = activity;
        this.testimonialsLists = testimonialsLists;
    }

    @NonNull
    @Override
    public TestimonialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return testimonialsLists.size();
    }

    class TestimonialHolder extends RecyclerView.ViewHolder {

        public TestimonialHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
