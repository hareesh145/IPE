package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemConstituencyDevelopmentWithFiltersBinding;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;

import java.util.ArrayList;

public class ViewConstituencyDevAdapter extends RecyclerView.Adapter<ViewConstituencyDevAdapter.ViewConstituencyHolder> {

    private final Activity activity;
    private final ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists;

    public ViewConstituencyDevAdapter(Activity activity, ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists) {
        this.activity = activity;
        this.constituencyDepartmentsLists = constituencyDepartmentsLists;
    }

    @NonNull
    @Override
    public ViewConstituencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewConstituencyHolder(ItemConstituencyDevelopmentWithFiltersBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewConstituencyHolder holder, int position) {
        holder.bind(constituencyDepartmentsLists.get(position));
    }

    @Override
    public int getItemCount() {
        return constituencyDepartmentsLists.size();
    }

    class ViewConstituencyHolder extends RecyclerView.ViewHolder {

        private com.indiapoliticaledge.databinding.ItemConstituencyDevelopmentWithFiltersBinding binding;

        public ViewConstituencyHolder(@NonNull ItemConstituencyDevelopmentWithFiltersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ConstituencyDepartmentsList constituencyDepartmentsList) {
            binding.prevYearTitle.setText("Before the Election " + constituencyDepartmentsList.year);
            binding.prevWorkDesc.setText(constituencyDepartmentsList.workDescription);
            binding.currentYearTitle.setText("After the Election " + constituencyDepartmentsList.currentYear);
            binding.currentWorkDesc.setText(constituencyDepartmentsList.currentWorkDescription);
        }
    }
}
