package com.indiapoliticaledge.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.utils.Utils;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageMLASubscription extends Fragment {
    RetrofitAPI retrofitAPI;
    ArrayList<UserInfo> usersList;
    PowerSpinnerView state_drop_down;
    final Calendar startDateCalendar = Calendar.getInstance();
    final Calendar endDateCalendar = Calendar.getInstance();
    EditText start_date, end_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_mla_subscription, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        state_drop_down = view.findViewById(R.id.state_drop_down);

        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");

        retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
        retrofitAPI.manageMembers(jsonObject).enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(Call<MembersResponse> call, Response<MembersResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().successCode.equals("200")) {
                        usersList = response.body().usersList;
                        ArrayList<String> mlaNames = new ArrayList<>();
                        for (UserInfo userInfo : usersList) {
                            mlaNames.add(userInfo.getFirstName() + " " + userInfo.getLastName());
                        }
                        state_drop_down.setItems(mlaNames);
                    }
                }
            }

            @Override
            public void onFailure(Call<MembersResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });

        start_date = view.findViewById(R.id.start_date);
        end_date = view.findViewById(R.id.end_date);

        DatePickerDialog.OnDateSetListener startDate = (view1, year, month, day) -> {
            startDateCalendar.set(Calendar.YEAR, year);
            startDateCalendar.set(Calendar.MONTH, month);
            startDateCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateStartDate();
        };


        DatePickerDialog.OnDateSetListener endDate = (view1, year, month, day) -> {
            endDateCalendar.set(Calendar.YEAR, year);
            endDateCalendar.set(Calendar.MONTH, month);
            endDateCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateEndDate();
        };

        end_date.setOnClickListener(view13 -> new DatePickerDialog(requireActivity(), endDate, endDateCalendar.get(Calendar.YEAR), endDateCalendar.get(Calendar.MONTH), endDateCalendar.get(Calendar.DAY_OF_MONTH)).show());

        start_date.setOnClickListener(view12 -> new DatePickerDialog(requireActivity(), startDate, startDateCalendar.get(Calendar.YEAR), startDateCalendar.get(Calendar.MONTH), startDateCalendar.get(Calendar.DAY_OF_MONTH)).show());


        view.findViewById(R.id.submit_btn).setOnClickListener(v -> {
            Utils.showProgessBar(requireActivity());
            JsonObject updateMLAJson = new JsonObject();
            updateMLAJson.addProperty("userId", getSelectedUserID(state_drop_down.getText().toString()));
            updateMLAJson.addProperty("startDate", start_date.getText().toString());
            updateMLAJson.addProperty("endDate", end_date.getText().toString());

            retrofitAPI.manageSubscription(updateMLAJson).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Utils.hideProgessBar();
                    Toast.makeText(requireActivity(), "Subscription updated successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Utils.hideProgessBar();
                }
            });


        });
    }

    private int getSelectedUserID(String mlaName) {
        if (usersList != null) {
            for (UserInfo userInfo : usersList) {
                if (mlaName.equals(userInfo.getFirstName() + " " + userInfo.getLastName())) {
                    return userInfo.userId;
                }
            }
        }
        return -1;
    }

    private void updateStartDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        start_date.setText(dateFormat.format(startDateCalendar.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        end_date.setText(dateFormat.format(endDateCalendar.getTime()));
    }
}
