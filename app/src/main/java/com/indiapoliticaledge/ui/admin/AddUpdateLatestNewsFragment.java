package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.SINGLE_LATEST_NEWS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddLatestNewsLayoutBinding;
import com.indiapoliticaledge.network.responsemodel.NewsList;
import com.indiapoliticaledge.ui.CandidateHomeScreen;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;

public class AddUpdateLatestNewsFragment extends Fragment {

    AddLatestNewsLayoutBinding binding;
    NewsList newsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddLatestNewsLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(SINGLE_LATEST_NEWS)) {
            if (requireActivity() instanceof MLAInfoDrawerScreen) {
                ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.update_constituency_news));
            } else if (requireActivity() instanceof CandidateHomeScreen) {
                ((CandidateHomeScreen) requireActivity()).setTitleText(getString(R.string.update_constituency_news));
            }
            newsList = (NewsList) bundle.get(SINGLE_LATEST_NEWS);
        } else {
            if (requireActivity() instanceof MLAInfoDrawerScreen) {
                ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_latest_news));
            } else if (requireActivity() instanceof CandidateHomeScreen) {
                ((CandidateHomeScreen) requireActivity()).setTitleText(getString(R.string.add_latest_news));
            }
        }

        if (newsList != null) {
            bindNewsList();
        }
    }

    private void bindNewsList() {
        binding.newsTitleTxt.setText(newsList.newsTitle);
        binding.newsDescriptionTxt.setText(newsList.newsDescription);
    }
}
