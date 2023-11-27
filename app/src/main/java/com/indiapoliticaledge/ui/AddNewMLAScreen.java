package com.indiapoliticaledge.ui;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.JsonObject;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.network.RetrofitAPI;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.Member;
import com.indiapoliticaledge.network.responsemodel.AddMemberResponse;
import com.indiapoliticaledge.network.responsemodel.ConstituencyResponse;
import com.indiapoliticaledge.network.responsemodel.DistrictResponse;
import com.indiapoliticaledge.network.responsemodel.StatesResponse;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewMLAScreen extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_CODE = 123;
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 141;
    Button submit_btn;
    EditText first_name_et, last_name_et, phone_number, party_edit;
    TextView start_date, end_date;
    PowerSpinnerView state_drop_down, district_drop_down, mandal_drop_down, constituency_drop_down;
    RetrofitAPI retrofitAPI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_mla_layout);
        retrofitAPI = RetrofitClient.getInstance(this).getRetrofitAPI();
        initFields();
        View view = findViewById(R.id.custom_title_view);
        TextView titleTxtzview = view.findViewById(R.id.title_txt);
        titleTxtzview.setText("Add MLA");

        submit_btn = findViewById(R.id.submit_btn);
        findViewById(R.id.add_remove_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });
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
            Member member = new Member();
            member.setFirstName(first_name_et.getText().toString());
            member.setLastName(last_name_et.getText().toString());
            member.setMobileNumber(phone_number.getText().toString());
            member.setActiveFlag("Y");
            member.setRoleName(Constants.INTERNAL);
            member.setPartyName(party_edit.getText().toString());
            member.setStartDate(start_date.getText().toString());


            retrofitAPI.addMember(member).enqueue(new Callback<AddMemberResponse>() {
                @Override
                public void onResponse(Call<AddMemberResponse> call, Response<AddMemberResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null && response.body().successCode.equals("200")) {

                        }
                    }
                }

                @Override
                public void onFailure(Call<AddMemberResponse> call, Throwable t) {

                }
            });
            startActivity(new Intent(AddNewMLAScreen.this, SubscriptionFeesScreen.class));
        });

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

    }

    private void getStates() {
        Utils.showProgessBar(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("countryId", 1);
        retrofitAPI.getStates(jsonObject).enqueue(new Callback<StatesResponse>() {
            @Override
            public void onResponse(Call<StatesResponse> call, Response<StatesResponse> response) {
                Utils.hideProgessBar();

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

            }

            @Override
            public void onFailure(Call<ConstituencyResponse> call, Throwable t) {

            }
        });

    }

    private void pickImage() {
        if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("scale", true);
            intent.putExtra("aspectX", 16);
            intent.putExtra("aspectY", 9);
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_STORAGE_REQUEST_CODE
                );
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            Uri uri = data.getData();
            if (uri != null) {
                File fileUri = uriToImageFile(uri);
            }
            if (uri != null) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // todo do something with bitmap
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage();
                }
                break;
            }
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
}
