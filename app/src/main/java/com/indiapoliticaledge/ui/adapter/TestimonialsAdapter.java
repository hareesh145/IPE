package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
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
        return new TestimonialHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testimonial, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestimonialHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return testimonialsLists.size();
    }

    class TestimonialHolder extends RecyclerView.ViewHolder {
        TextView mla_name;
        TextView constituency_name_txt, party_name_txt, mobile_number_txt;

        public TestimonialHolder(@NonNull View itemView) {
            super(itemView);
            mobile_number_txt = itemView.findViewById(R.id.mobile_number_txt);
            mla_name = itemView.findViewById(R.id.mla_name);
        }
    }
}
