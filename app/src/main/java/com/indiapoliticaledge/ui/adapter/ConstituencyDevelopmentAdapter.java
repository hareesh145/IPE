package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.SINGLE_CONSTITUENCY_DEV;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemConstituencyDevelopmentBinding;
import com.indiapoliticaledge.model.ConstituencyDepartmentsList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.admin.AddUpdateConstituencyDevelopmentFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class ConstituencyDevelopmentAdapter extends RecyclerView.Adapter<ConstituencyDevelopmentAdapter.ConstituencyHolder> {


    private final Activity activity;
    private final ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists;
    private String userInfo;

    public ConstituencyDevelopmentAdapter(Activity activity, ArrayList<ConstituencyDepartmentsList> constituencyDepartmentsLists, String userInfo) {
        this.activity = activity;
        this.constituencyDepartmentsLists = constituencyDepartmentsLists;
        this.userInfo = userInfo;
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

            binding.editIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.USER_INFO, userInfo);
                    bundle.putSerializable(SINGLE_CONSTITUENCY_DEV, constituencyDepartmentsLists.get(getAdapterPosition()));
                    AddUpdateConstituencyDevelopmentFragment addUpdateConstituencyDevelopmentFragment = new AddUpdateConstituencyDevelopmentFragment();
                    addUpdateConstituencyDevelopmentFragment.setArguments(bundle);
                    ((MLAInfoDrawerScreen) activity).updateFragment(addUpdateConstituencyDevelopmentFragment);
                }
            });
            binding.deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                }
            });
        }

        public void bind(ConstituencyDepartmentsList constituencyDepartmentsList) {
            binding.workDescription.setText("Description : " + constituencyDepartmentsList.workDescription);
            binding.workAmount.setText("Work Amount : " + constituencyDepartmentsList.getWorkAmount());
            binding.remarks.setText(constituencyDepartmentsList.remarks);
            binding.workLocation.setText("Mandal/Village Name : " + constituencyDepartmentsList.workLocation);

        }
    }
}
