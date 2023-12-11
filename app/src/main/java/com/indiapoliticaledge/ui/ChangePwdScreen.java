package com.indiapoliticaledge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.ChangePassword;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePwdScreen extends Fragment {
    EditText current_pwd_edit, new_pwd_edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.change_pwd_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        current_pwd_edit = view.findViewById(R.id.current_pwd_edit);
        new_pwd_edit = view.findViewById(R.id.new_pwd_edit);

        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);


        view.findViewById(R.id.submit_btn).setOnClickListener(v -> {
            Utils.showProgessBar(requireContext());
            ChangePassword changePassword = new ChangePassword();
            changePassword.setCurrentPassword(current_pwd_edit.getText().toString());
            changePassword.setNewPassword(new_pwd_edit.getText().toString());
            changePassword.setId(String.valueOf(userInfo.getUserId()));
            changePassword.setRoleType(userInfo.getRoleName());
            RetrofitAPI retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
            retrofitAPI.changePassword(changePassword).enqueue(new Callback<AddMemberResponse>() {
                @Override
                public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                    Utils.hideProgessBar();
                    try {
                        Toast.makeText(requireActivity(), "Password Updated successfully", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<AddMemberResponse> call, Throwable t) {
                    Utils.hideProgessBar();
                }
            });
        });
    }
}
