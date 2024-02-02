package com.indiapoliticaledge.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AddBoothlevelGroupBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.ui.MLAInfoDrawerScreen;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBoothPoolManagement extends Fragment {

    AddBoothlevelGroupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddBoothlevelGroupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MLAInfoDrawerScreen) requireActivity()).setTitleText(getString(R.string.add_booth_poll_group));
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.groupNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), getString(R.string.pls_group_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.mandalNameEdit.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), getString(R.string.pls_mandal_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.villageNameEdit.getText().toString().isEmpty()) {
                    Toast.makeText(requireActivity(), getString(R.string.pls_village_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                Utils.showProgessBar(requireActivity());
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("constituencyId", userInfo.constituencyId);
                jsonObject.addProperty("groupName", binding.groupNameEt.getText().toString());
                jsonObject.addProperty("mandalName", binding.mandalNameEdit.getText().toString());
                jsonObject.addProperty("villageName", binding.villageNameEdit.getText().toString());
                RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().addBoothLevelGroup(jsonObject).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            Toast.makeText(requireActivity(), "Booth Level Group Added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Utils.hideProgessBar();
                    }
                });
            }
        });
    }
}
