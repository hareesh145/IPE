package com.indiapoliticaledge.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomPowerSpinnerAdapter extends RecyclerView.Adapter<CustomPowerSpinnerAdapter.PowerSpinnerHolder> {

    @NonNull
    @Override
    public PowerSpinnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PowerSpinnerHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PowerSpinnerHolder extends RecyclerView.ViewHolder {

        public PowerSpinnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
