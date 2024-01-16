package com.indiapoliticaledge.ui.admin;

import static com.indiapoliticaledge.utils.Constants.SINGLE_NOTICE_MESSAGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddNoticeBoardFragmentBinding;
import com.indiapoliticaledge.model.NoticeMessagesList;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;

public class AddUpdateNoticeBoardFragment extends Fragment {
    AddNoticeBoardFragmentBinding binding;

    NoticeMessagesList noticeMessagesList;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddNoticeBoardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments.containsKey(SINGLE_NOTICE_MESSAGE)) {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.update_notice_messages));
            noticeMessagesList = (NoticeMessagesList) arguments.get(SINGLE_NOTICE_MESSAGE);
        } else {
            ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_notice_messages));
        }

        if (noticeMessagesList != null) {
            bindNoticeMessageData();
        }

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noticeMessagesList != null) {

                } else {
                    //Add notice message API calling
//                    RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().
                }
            }
        });

    }

    private void bindNoticeMessageData() {
        try {
            binding.noticeTitle.setText(noticeMessagesList.noticeTitle);
            binding.noticeMessage.setText(noticeMessagesList.noticeMessage);
            if (noticeMessagesList.showDate != null) {
                binding.showDate.setText(noticeMessagesList.showDate.split("\\s")[0]);
            }
        } catch (Exception e) {

        }

    }
}
