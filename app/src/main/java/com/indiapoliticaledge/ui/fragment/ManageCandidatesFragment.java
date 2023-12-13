package com.indiapoliticaledge.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.ui.CandidateProfile;
import com.indiapoliticaledge.ui.ViewCandidatesAdapter;

import java.util.ArrayList;

public class ManageCandidatesFragment extends Fragment {
    RecyclerView view_mlas_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manage_candidates_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_mlas_list = view.findViewById(R.id.view_mlas_list);



        view_mlas_list.setAdapter(new ViewCandidatesAdapter(requireActivity(), new ArrayList<>()));
        view.findViewById(R.id.add_mla_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), CandidateProfile.class));
            }
        });
    }
}
