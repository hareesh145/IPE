package com.indiapoliticaledge.ui.superadmin;

import static com.indiapoliticaledge.utils.Constants.USER_INFO;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.ui.AddNewMLAScreen;
import com.indiapoliticaledge.ui.ViewMLAAdapter;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMLAListFragment extends Fragment {
    RecyclerView view_mlas_list;
    FloatingActionButton add_mla_button;
    private RetrofitAPI retrofitAPI;
    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_mla_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view_mlas_list = view.findViewById(R.id.view_mlas_list);
        add_mla_button = view.findViewById(R.id.add_mla_button);


        add_mla_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddNewMLAScreen.class);
                intent.putExtra(USER_INFO, new Gson().toJson(userInfo));
                startActivity(intent);
            }
        });
    }

    private void getAllMLAs() {
        Bundle bundle = getArguments();
        String jsonObjectUser = bundle.getString(Constants.USER_INFO);
        userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);

        retrofitAPI = RetrofitClient.getInstance(requireActivity()).getRetrofitAPI();
        Utils.showProgessBar(requireActivity());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");
        retrofitAPI.manageMembers(jsonObject).enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(Call<MembersResponse> call, Response<MembersResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().successCode.equals("200")) {
                        ArrayList<UserInfo> usersList = response.body().usersList;
                        view_mlas_list.setAdapter(new ViewMLAAdapter(ViewMLAListFragment.this, requireActivity(), usersList));
                    }
                }
            }

            @Override
            public void onFailure(Call<MembersResponse> call, Throwable t) {
                Utils.hideProgessBar();
//                ArrayList<UserInfo> usersList = new ArrayList<>();
//                view_mlas_list.setAdapter(new ViewMLAAdapter(ViewMLAListScreen.this, usersList));
            }
        });
    }

    public void deleteMember(UserInfo userInfo) {
        Utils.showProgessBar(requireActivity());
        RetrofitClient.getInstance(requireActivity()).getRetrofitAPI().updateMember(userInfo).enqueue(new Callback<AddMemberResponse>() {
            @Override
            public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    getAllMLAs();
                }
            }

            @Override
            public void onFailure(Call<AddMemberResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllMLAs();
    }
}
