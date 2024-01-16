package com.indiapoliticaledge.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;
import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.ConstituencyList;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.network.responsemodel.ConstituencyResponse;
import com.indiapoliticaledge.network.responsemodel.DistrictResponse;
import com.indiapoliticaledge.network.responsemodel.DistrictsList;
import com.indiapoliticaledge.network.responsemodel.StatesList;
import com.indiapoliticaledge.network.responsemodel.StatesResponse;
import com.indiapoliticaledge.network.responsemodel.ViewMemberResponse;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMLAScreen extends AppCompatActivity {
    Button submit_btn;
    EditText first_name_et, last_name_et, phone_number, party_edit;
    TextView start_date, end_date;
    PowerSpinnerView state_drop_down, district_drop_down, mandal_drop_down, constituency_drop_down;
    RetrofitAPI retrofitAPI;
    private static final int PICK_IMAGE_REQUEST_CODE = 123;
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 141;
    private CircleImageView img;
    private static final int PROFILE_IMAGE_REQ_CODE = 123;
    private Uri mProfileUri;
    StatesResponse statesResponse;
    DistrictResponse districtResponse;

    ConstituencyResponse constituencyResponse;

    String constituencyName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_mla_layout);
        retrofitAPI = RetrofitClient.getInstance(this).getRetrofitAPI();
        initFields();

        View view = findViewById(R.id.custom_title_view);
        TextView title_txt = view.findViewById(R.id.title_txt);
        title_txt.setText(getText(R.string.update_mla));

        RetrofitAPI retrofitAPI = RetrofitClient.getInstance(this).getRetrofitAPI();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", getIntent().getIntExtra("user_id", -1));
        Utils.showProgessBar(this);
        retrofitAPI.getMember(jsonObject).enqueue(new Callback<ViewMemberResponse>() {
            @Override
            public void onResponse(Call<ViewMemberResponse> call, Response<ViewMemberResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    ViewMemberResponse viewMemberResponse = response.body();
                    if (viewMemberResponse != null && viewMemberResponse.successCode.equals("200")) {
                        UserInfo userInfo = viewMemberResponse.getUserInfo();
                        if (userInfo != null) {
                            bindUserInfo(viewMemberResponse);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ViewMemberResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });


        findViewById(R.id.add_remove_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickProfileImage();
            }
        });
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickProfileImage();
            }
        });
        submit_btn = findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(v -> {

            if (first_name_et.getText().toString().isEmpty()) {
                Utils.showSnackBar(first_name_et, "Please Enter First Name");
                return;
            }
            if (last_name_et.getText().toString().isEmpty()) {
                Utils.showSnackBar(last_name_et, "Please Enter Last Name");
                return;
            }
            if (phone_number.getText().toString().isEmpty()) {
                Utils.showSnackBar(phone_number, "Please Enter Mobile Number");
                return;
            }
            if (party_edit.getText().toString().isEmpty()) {
                Utils.showSnackBar(party_edit, "Please Enter Party Name");
                return;
            }


            Utils.showProgessBar(this);
            userInfo.setFirstName(first_name_et.getText().toString());
            userInfo.setLastName(last_name_et.getText().toString());
            userInfo.setMobileNumber(phone_number.getText().toString());
            userInfo.setActiveFlag("Y");
            userInfo.setRoleName(Constants.INTERNAL);
            userInfo.setPartyName(party_edit.getText().toString());
            userInfo.setStartDate(start_date.getText().toString());


            retrofitAPI.updateMember(userInfo).enqueue(new Callback<AddMemberResponse>() {
                @Override
                public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().successCode.equals("200")) {

                            Utils.showSnackBar(submit_btn, "Updated Successfully");
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddMemberResponse> call, Throwable t) {

                }
            });
        });

        state_drop_down.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>)
                (oldIndex, oldItem, newIndex, newItem) -> {
                    if (newIndex > 0) {
                        int stateId = getStateId(newItem);
                        if (stateId != -1) {
                            getDistricts(stateId);
                        }
                    }
                });

        district_drop_down.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>)
                (oldIndex, oldItem, newIndex, newItem) -> {
                    if (newIndex > 0) {
                        int stateId = getDistrictId(newItem);
                        if (stateId != -1) {
                            getConstituencies(stateId);
                        }
                    }
                });


        constituency_drop_down.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>)
                (oldIndex, oldItem, newIndex, newItem) -> {
                    if (newIndex > 0) {
                        constituencyName = newItem;
                    } else {
                        constituencyName = "";
                    }
                });


    }

    UserInfo userInfo;

    private void bindUserInfo(ViewMemberResponse viewMemberResponse) {
        userInfo = viewMemberResponse.userInfo;
        first_name_et.setText(userInfo.getFirstName());
        last_name_et.setText(userInfo.getLastName());
        phone_number.setText(userInfo.getMobileNumber());
        start_date.setText(userInfo.getStartDate());
        start_date.setText(userInfo.getEndDate());
        party_edit.setText(userInfo.getPartyName());

        Glide.with(this).load(viewMemberResponse.userInfo.profilePhotoUrl).placeholder(R.drawable.ic_user_logo).into(img);

        getStates();
    }

    private void initFields() {
        first_name_et = findViewById(R.id.first_name_et);
        last_name_et = findViewById(R.id.last_name_et);
        phone_number = findViewById(R.id.phone_number);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);
        state_drop_down = findViewById(R.id.state_drop_down);
        district_drop_down = findViewById(R.id.district_drop_down);
        mandal_drop_down = findViewById(R.id.mandal_drop_down);
        constituency_drop_down = findViewById(R.id.constituency_drop_down);
        party_edit = findViewById(R.id.party_edit);
        img = findViewById(R.id.img);

    }


    public void pickProfileImage() {
        ImagePicker.with(this)
                .cropSquare()
                .setImageProviderInterceptor(new Function1<ImageProvider, Unit>() {
                    @Override
                    public Unit invoke(ImageProvider imageProvider) {
                        Log.d("ImagePicker", "Selected ImageProvider: " + imageProvider.toString());
                        return null;
                    }
                }).setDismissListener(new DismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.d("ImagePicker", "Dialog Dismiss");
                    }
                })
                // Image resolution will be less than 512 x 512
                .maxResultSize(200, 200)
                .start(PROFILE_IMAGE_REQ_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // Uri object will not be null for RESULT_OK
            Uri uri = data.getData();

            switch (requestCode) {
                case PROFILE_IMAGE_REQ_CODE:
                    mProfileUri = uri;
                    Glide.with(this).load(uri).into(img);
                    break;

            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


    private File uriToImageFile(Uri uri) {
        String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                return new File(filePath);
            }
            cursor.close();
        }
        return null;
    }

    private Bitmap uriToBitmap(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            return null;
        }
    }

    private int getStateId(String newItem) {
        for (StatesList statesList :
                statesResponse.getStatesList()) {
            if (statesList.getName().equals(newItem)) {
                return statesList.id;
            }
        }
        return -1;
    }

    private int getDistrictId(String newItem) {
        for (DistrictsList statesList :
                districtResponse.getDistrictsList()) {
            if (statesList.getName().equals(newItem)) {
                return statesList.id;
            }
        }
        return -1;
    }

    private int getConstituencyId(String newItem) {
        for (ConstituencyList statesList :
                constituencyResponse.getConstituencyList()) {
            if (statesList.getName().equals(newItem)) {
                return statesList.id;
            }
        }
        return -1;
    }

    private void getStates() {
        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("countryId", 1);
        retrofitAPI.getStates(jsonObject).enqueue(new Callback<StatesResponse>() {
            @Override
            public void onResponse(Call<StatesResponse> call, Response<StatesResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    statesResponse = response.body();
                    ArrayList<String> states = new ArrayList<>();
                    states.add("Select State");
                    for (StatesList statesList :
                            statesResponse.statesList) {
                        states.add(statesList.getName());
                    }
                    state_drop_down.setItems(states);
                }
            }

            @Override
            public void onFailure(Call<StatesResponse> call, Throwable t) {
                Utils.hideProgessBar();

            }
        });
    }

    private void getDistricts(int stateID) {
        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stateId", stateID);
        retrofitAPI.getDistricts(jsonObject).enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                Utils.hideProgessBar();

                if (response.isSuccessful()) {
                    districtResponse = response.body();
                    ArrayList<String> states = new ArrayList<>();
                    states.add("Select District");
                    for (DistrictsList statesList :
                            districtResponse.getDistrictsList()) {
                        states.add(statesList.getName());
                    }
                    district_drop_down.setItems(states);
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Utils.hideProgessBar();

            }
        });
    }


    private void getConstituencies(int districtID) {
        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("districtId", districtID);
        retrofitAPI.getConstituencies(jsonObject).enqueue(new Callback<ConstituencyResponse>() {
            @Override
            public void onResponse(Call<ConstituencyResponse> call, Response<ConstituencyResponse> response) {
                Utils.hideProgessBar();
                if (response.isSuccessful()) {
                    constituencyResponse = response.body();
                    ArrayList<String> states = new ArrayList<>();
                    states.add("Select Constituency");
                    for (ConstituencyList statesList :
                            constituencyResponse.getConstituencyList()) {
                        states.add(statesList.getName());
                    }
                    constituency_drop_down.setItems(states);
                }
            }

            @Override
            public void onFailure(Call<ConstituencyResponse> call, Throwable t) {
                Utils.hideProgessBar();
            }
        });

    }
}
