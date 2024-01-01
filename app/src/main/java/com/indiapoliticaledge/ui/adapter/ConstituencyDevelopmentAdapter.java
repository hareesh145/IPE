package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemConstituencyDevelopmentBinding;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;

import java.util.ArrayList;

public class ConstituencyDevelopmentAdapter extends RecyclerView.Adapter<ConstituencyDevelopmentAdapter.ConstituencyHolder> {


    private final Activity activity;
    private final ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists;

    public ConstituencyDevelopmentAdapter(Activity activity, ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists) {
        this.activity = activity;
        this.constituencyDepartmentsLists = constituencyDepartmentsLists;
    }

    @NonNull
    @Override
    public ConstituencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ConstituencyHolder(ItemConstituencyDevelopmentBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConstituencyHolder holder, int position) {
        holder.bind(constituencyDepartmentsLists.get(position));
    }

    @Override
    public int getItemCount() {
        return constituencyDepartmentsLists.size();
    }

    class ConstituencyHolder extends RecyclerView.ViewHolder {

        private ItemConstituencyDevelopmentBinding binding;

        public ConstituencyHolder(@NonNull ItemConstituencyDevelopmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ConstituencyDepartmentsList constituencyDepartmentsList) {
            binding.workDescription.setText("Description : "+constituencyDepartmentsList.workDescription);
            binding.workAmount.setText("Work Amount : " + constituencyDepartmentsList.getWorkAmount());
            binding.remarks.setText(constituencyDepartmentsList.remarks);
            binding.workLocation.setText("Mandal/Village Name : "+constituencyDepartmentsList.workLocation);

        }
    }
}
