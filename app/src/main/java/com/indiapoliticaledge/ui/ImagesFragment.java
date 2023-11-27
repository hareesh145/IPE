package com.indiapoliticaledge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;

public class ImagesFragment extends Fragment {
    RecyclerView images_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.images_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        images_list = view.findViewById(R.id.images_list);
        images_list.setLayoutManager(new GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false));

        images_list.setAdapter(new ImagesAdapter());
    }
}
