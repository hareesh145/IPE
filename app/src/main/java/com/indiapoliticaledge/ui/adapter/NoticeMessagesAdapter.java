package com.indiapoliticaledge.ui.adapter;

import static com.indiapoliticaledge.utils.Constants.SINGLE_NOTICE_MESSAGE;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.NoticeMessagesList;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.ui.admin.AddUpdateNoticeBoardFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.ArrayList;

public class NoticeMessagesAdapter extends RecyclerView.Adapter<NoticeMessagesAdapter.NoticeMessageHolder> {


    private final Activity activity;
    private final ArrayList<NoticeMessagesList> noticeMessagesLists;
    private String userInfo;

    public NoticeMessagesAdapter(Activity activity, ArrayList<NoticeMessagesList> noticeMessagesLists, String userInfo) {
        this.activity = activity;
        this.noticeMessagesLists = noticeMessagesLists;
        this.userInfo = userInfo;
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
            itemView.findViewById(R.id.edit_icon).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.USER_INFO, userInfo);
                    bundle.putSerializable(SINGLE_NOTICE_MESSAGE, noticeMessagesLists.get(getAdapterPosition()));
                    AddUpdateNoticeBoardFragment addUpdateNoticeBoardFragment = new AddUpdateNoticeBoardFragment();
                    addUpdateNoticeBoardFragment.setArguments(bundle);
                    ((MLAInfoDrawerScreen) activity).updateFragment(addUpdateNoticeBoardFragment);
                }
            });

            itemView.findViewById(R.id.delete_icon).setOnClickListener(new View.OnClickListener() {
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

        public void bind(NoticeMessagesList noticeMessagesList) {
            mla_name.setText(noticeMessagesList.noticeTitle);
            constituency_name_txt.setText(noticeMessagesList.noticeMessage);
            if (noticeMessagesList.createdDate != null) {
                party_name_txt.setText("Created Date : " + noticeMessagesList.createdDate.split("\\s")[0]);
            }
        }
    }
}
