package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.TESTIMONIAL;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.network.responsemodel.TestimonialsList;
import com.indiapoliticaledge.ui.SuperAdminScreen;
import com.indiapoliticaledge.ui.fragment.TestimonialsFragment;
import com.indiapoliticaledge.ui.fragment.UpdateTestimonialFragment;

import java.util.ArrayList;

public class TestimonialsAdapter extends RecyclerView.Adapter<TestimonialsAdapter.TestimonialHolder> {

    private TestimonialsFragment fragment;
    private final Activity activity;
    private final ArrayList<TestimonialsList> testimonialsLists;

    public TestimonialsAdapter(TestimonialsFragment fragment, Activity activity, ArrayList<TestimonialsList> testimonialsLists) {
        this.fragment = fragment;
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
        holder.bind(testimonialsLists.get(position));
    }

    @Override
    public int getItemCount() {
        return testimonialsLists.size();
    }

    class TestimonialHolder extends RecyclerView.ViewHolder {
        TextView mla_name;
        TextView constituency_name_txt, party_name_txt, mobile_number_txt;
        RatingBar rating_bar;
        ImageView profile_image;
        ImageView edit_icon;

        public TestimonialHolder(@NonNull View itemView) {
            super(itemView);
            mobile_number_txt = itemView.findViewById(R.id.mobile_number_txt);
            mla_name = itemView.findViewById(R.id.mla_name);
            rating_bar = itemView.findViewById(R.id.rating_bar);
            constituency_name_txt = itemView.findViewById(R.id.constituency_name_txt);
            party_name_txt = itemView.findViewById(R.id.party_name_txt);
            profile_image = itemView.findViewById(R.id.profile_image);
            edit_icon = itemView.findViewById(R.id.edit_icon);
            edit_icon.setOnClickListener(v -> {
                UpdateTestimonialFragment testimonialFragment = new UpdateTestimonialFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(TESTIMONIAL, testimonialsLists.get(getAdapterPosition()));
                testimonialFragment.setArguments(bundle);
                ((SuperAdminScreen) activity).updateFragment(testimonialFragment);
            });
            itemView.findViewById(R.id.delete_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                    builder1.setMessage("Are you sure you want to delete?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("Yes", (dialog, which) -> {
                        dialog.cancel();
                        fragment.deleteTestimonial(testimonialsLists.get(getAdapterPosition()));

                    });
                    builder1.setNegativeButton(
                            "No",
                            (dialog, id) -> dialog.cancel());

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

        }

        public void bind(TestimonialsList testimonialsList) {
            mla_name.setText(testimonialsList.firstName + " " + testimonialsList.lastName);
            constituency_name_txt.setText(testimonialsList.testimonialsText);
            rating_bar.setRating(testimonialsList.rating);
            mobile_number_txt.setText(testimonialsList.city + "," + testimonialsList.state);
            Glide.with(activity).load(testimonialsList.testimonialsPic).placeholder(R.drawable.ic_user_logo).into(profile_image);
        }
    }
}
