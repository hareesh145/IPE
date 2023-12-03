package com.indiapoliticaledge.ui;

import static com.indiapoliticaledge.utils.Constants.USER_INFO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.network.RetrofitClient;
import com.indiapoliticaledge.network.requestmodel.SignInModel;
import com.indiapoliticaledge.network.responsemodel.SignInResponseModel;
import com.indiapoliticaledge.utils.Constants;
import com.indiapoliticaledge.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {
    Button submit_btn;
    EditText phone_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        submit_btn = findViewById(R.id.submit_btn);
        phone_number = findViewById(R.id.phone_number);

        submit_btn.setOnClickListener(v -> {
            if (phone_number.getText().toString().isEmpty()) {
                Snackbar.make(phone_number, "Please Enter Phone Number", Snackbar.LENGTH_LONG).show();
                return;
            }
            try {
                Utils.showProgessBar(this);
                SignInModel signInModel = new SignInModel();
                signInModel.setMobileNumber(phone_number.getText().toString());
                RetrofitClient retrofitClient = RetrofitClient.getInstance(this);
                retrofitClient.getRetrofitAPI().signIn(signInModel).enqueue(new Callback<SignInResponseModel>() {
                    @Override
                    public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                        Utils.hideProgessBar();
                        if (response.isSuccessful()) {
                            SignInResponseModel signInResponseModel = response.body();
                            if (signInResponseModel != null && signInResponseModel.successCode.equals("200")) {
                                if (signInResponseModel.userInfo != null &&
                                        signInResponseModel.userInfo.getRoleName().equals(Constants.SUPER_ADMIN)) {
                                    navigateHome();
                                } else if (signInResponseModel.userInfo.getRoleName().equals(Constants.CANDIDATE)) {
                                    navigateToCandidate(signInResponseModel);
                                }
                            }
                        } else {
                            SignInResponseModel signInResponseModel = response.body();
                            if (signInResponseModel != null) {
                                Utils.showSnackBarAlert(submit_btn, signInResponseModel.getErrorMessage());
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponseModel> call, Throwable t) {
                        Utils.hideProgessBar();
                        navigateHome();
                    }
                });
            } catch (Exception e) {

            }
        });

    }

    private void navigateToCandidate(SignInResponseModel signInResponseModel) {
        Intent intent = new Intent(LoginScreen.this, CandidateHomeScreen.class);
        intent.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
        startActivity(intent);
        finish();
    }

    private void navigateHome() {
        Intent intent = new Intent(LoginScreen.this, ViewMLAListScreen.class);
        startActivity(intent);
        finish();
    }
}
