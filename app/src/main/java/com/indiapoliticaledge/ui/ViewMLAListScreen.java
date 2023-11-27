package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.ChangePassword;
import com.indiapoliticaledge.network.responsemodel.MembersResponse;
import com.indiapoliticaledge.utils.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMLAListScreen extends AppCompatActivity {
    RecyclerView view_mlas_list;
    FloatingActionButton add_mla_button;
    RetrofitAPI retrofitAPI;
    ImageView menu_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mla_list_layout);

        view_mlas_list = findViewById(R.id.view_mlas_list);
        add_mla_button = findViewById(R.id.add_mla_button);
        menu_icon = findViewById(R.id.menu_icon);


        retrofitAPI = RetrofitClient.getInstance(this).getRetrofitAPI();
        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deleteFlag", "N");
        retrofitAPI.manageMembers(jsonObject).enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(Call<MembersResponse> call, Response<MembersResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().successCode.equals("200")) {
                        ArrayList<UserInfo> usersList = response.body().usersList;
                        view_mlas_list.setAdapter(new ViewMLAAdapter(ViewMLAListScreen.this, usersList));
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

        menu_icon.setOnClickListener(v -> {
            // Initializing the popup menu and giving the reference as current context
            PopupMenu popupMenu = new PopupMenu(ViewMLAListScreen.this, menu_icon);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.super_admin_options, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getTitle().equals("Logout")) {
                        finish();
                        startActivity(new Intent(ViewMLAListScreen.this, LoginScreen.class));
                    }else if(menuItem.getTitle().equals("MLA Subscription")){
                        startActivity(new Intent(ViewMLAListScreen.this, ManageMLASubscription.class));
                    }else if(menuItem.getTitle().equals("Update Password")){
                        startActivity(new Intent(ViewMLAListScreen.this, ChangePwdScreen.class));
                    }
                    return true;
                }
            });
            // Showing the popup menu
            popupMenu.show();
        });

        add_mla_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewMLAListScreen.this, AddNewMLAScreen.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.super_admin_options, menu);
        return true;
    }
}
