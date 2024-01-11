package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.databinding.ItemNoticeMessageBinding;
import com.indiapoliticaledge.model.NoticeMessagesList;

import java.util.List;

public class HomeNoticeAdapter extends RecyclerView.Adapter<HomeNoticeAdapter.HomeNoticeHolder> {

    private final Activity activity;
    private final List<NoticeMessagesList> noticeMessagesLists;

    public HomeNoticeAdapter(Activity activity, List<NoticeMessagesList> noticeMessagesLists) {
        this.activity = activity;
        this.noticeMessagesLists = noticeMessagesLists;
    }

    @NonNull
    @Override
    public HomeNoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new HomeNoticeHolder(ItemNoticeMessageBinding.inflate(activity.getLayoutInflater(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeNoticeHolder holder, int position) {
        NoticeMessagesList noticeMessagesList = noticeMessagesLists.get(position);
        holder.bindData(noticeMessagesList);
    }

    @Override
    public int getItemCount() {
        return noticeMessagesLists.size();
    }

    class HomeNoticeHolder extends RecyclerView.ViewHolder {

        private com.indiapoliticaledge.databinding.ItemNoticeMessageBinding binding;

        public HomeNoticeHolder(@NonNull ItemNoticeMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(NoticeMessagesList noticeMessagesList) {
            binding.noticeTitleTxt.setText(noticeMessagesList.noticeTitle);
            binding.noticeDateTxt.setText(noticeMessagesList.createdDate.split("\\s+")[0]);
            binding.noticeDescTxt.setText(noticeMessagesList.noticeMessage);
        }
    }
}
