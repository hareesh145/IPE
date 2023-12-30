package com.indiapoliticaledge.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.NoticeMessagesList;

import java.util.ArrayList;

public class NoticeMessagesAdapter extends RecyclerView.Adapter<NoticeMessagesAdapter.NoticeMessageHolder> {


    private final Activity activity;
    private final ArrayList<NoticeMessagesList> noticeMessagesLists;

    public NoticeMessagesAdapter(Activity activity, ArrayList<NoticeMessagesList> noticeMessagesLists) {
        this.activity = activity;
        this.noticeMessagesLists = noticeMessagesLists;
    }

    @NonNull
    @Override
    public NoticeMessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticeMessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice_messages, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeMessageHolder holder, int position) {
        holder.bind(noticeMessagesLists.get(position));
    }

    @Override
    public int getItemCount() {
        return noticeMessagesLists.size();
    }

    class NoticeMessageHolder extends RecyclerView.ViewHolder {
        TextView mla_name, constituency_name_txt, party_name_txt;

        public NoticeMessageHolder(@NonNull View itemView) {
            super(itemView);
            mla_name = itemView.findViewById(R.id.mla_name);
            constituency_name_txt = itemView.findViewById(R.id.constituency_name_txt);
            party_name_txt = itemView.findViewById(R.id.party_name_txt);
        }

        public void bind(NoticeMessagesList noticeMessagesList) {
            mla_name.setText(noticeMessagesList.noticeTitle);
            constituency_name_txt.setText(noticeMessagesList.noticeMessage);
            if (noticeMessagesList.createdDate != null) {
                party_name_txt.setText("Created Date : " + noticeMessagesList.createdDate.split("\\s")[0]);
            }
        }
    }
}
