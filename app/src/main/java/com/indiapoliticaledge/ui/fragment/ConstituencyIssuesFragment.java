package com.indiapoliticaledge.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstituencyIssuesFragment extends Fragment {

    private static final String TAG = ConstituencyIssuesFragment.class.getSimpleName();
    EditText raise_your_issue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.raise_your_issues_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        raise_your_issue = view.findViewById(R.id.raise_your_issue);

        view.findViewById(R.id.submit_btn).setOnClickListener(v -> {

            Bundle bundle = getArguments();
            String jsonObjectUser = bundle.getString(Constants.USER_INFO);
            UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userId", userInfo.getUserId());
            jsonObject.addProperty("constituencyId", userInfo.getConstituencyId());
            jsonObject.addProperty("describeIssue", raise_your_issue.getText().toString());
            jsonObject.addProperty("sarpanchName", userInfo.getUserId());
            RetrofitClient.getInstance(requireContext()).getRetrofitAPI().addConstituencyIssues(jsonObject).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    try {
                        Utils.hideProgessBar();
                        Log.d(TAG, "onResponse: " + response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        });


    }
}
