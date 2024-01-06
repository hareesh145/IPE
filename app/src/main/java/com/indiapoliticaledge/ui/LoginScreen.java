package com.indiapoliticaledge.ui;

import static com.indiapoliticaledge.utils.Constants.USER_INFO;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {
    Button submit_btn;
    EditText phone_number, password_text;
    private Locale myLocale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        submit_btn = findViewById(R.id.submit_btn);
        phone_number = findViewById(R.id.phone_number);
        password_text = findViewById(R.id.password_edit);

        submit_btn.setOnClickListener(v -> {
            Utils.hideKeyboard(LoginScreen.this);
            if (phone_number.getText().toString().isEmpty()) {
                Snackbar.make(phone_number, "Please Enter Phone Number", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (password_text.getText().toString().isEmpty()) {
                Snackbar.make(phone_number, "Please Enter Password", Snackbar.LENGTH_LONG).show();
                return;
            }

            try {
                Utils.showProgessBar(this);
                SignInModel signInModel = new SignInModel();
                signInModel.setMobileNumber(phone_number.getText().toString());
                signInModel.setPassword(password_text.getText().toString());
                RetrofitClient retrofitClient = RetrofitClient.getInstance(this);
                retrofitClient.getRetrofitAPI().signIn(signInModel).enqueue(new Callback<SignInResponseModel>() {
                    @Override
                    public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                        Utils.hideProgessBar();
                        SignInResponseModel signInResponseModel = response.body();
                        if (response.isSuccessful()) {
                            if (signInResponseModel != null && signInResponseModel.getUserInfo() != null) {
                                if (signInResponseModel.userInfo != null &&
                                        signInResponseModel.userInfo.getRoleName().equals(Constants.SUPER_ADMIN)) {
                                    navigateHome(signInResponseModel);
                                } else if (signInResponseModel.userInfo.getRoleName().equals(Constants.CANDIDATE)) {
                                    navigateToCandidate(signInResponseModel);
                                } else if (signInResponseModel.userInfo.getRoleName().equals(Constants.ADMIN)) {
                                    navigateToAdmin(signInResponseModel);
                                }
                            } else {
                                if (signInResponseModel.getErrorMessage() != null) {
                                    Utils.showSnackBarAlert(submit_btn, signInResponseModel.getErrorMessage());
                                }
                            }
                        } else {
                            if (signInResponseModel != null && signInResponseModel.getErrorMessage() != null) {
                                Utils.showSnackBarAlert(submit_btn, signInResponseModel.getErrorMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponseModel> call, Throwable t) {
                        Utils.hideProgessBar();
                        navigateHome(null);
                    }
                });
            } catch (Exception e) {

            }
        });

    }

    private void navigateToAdmin(SignInResponseModel signInResponseModel) {
        if (signInResponseModel.userInfo.language.equalsIgnoreCase("TELUGU")) {
            myLocale = new Locale("te");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }
        Intent refresh = new Intent(this, MLAInfoDrawerScreen.class);
        refresh.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
        startActivity(refresh);
        finish();
    }

    private void navigateToCandidate(SignInResponseModel signInResponseModel) {
        if (signInResponseModel.userInfo.language.equalsIgnoreCase("TELUGU")) {
            myLocale = new Locale("te");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }
        Intent intent = new Intent(LoginScreen.this, CandidateHomeScreen.class);
        intent.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
        startActivity(intent);
        finish();
    }

    private void navigateHome(SignInResponseModel signInResponseModel) {
        Intent intent = new Intent(LoginScreen.this, SuperAdminScreen.class);
        intent.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
        startActivity(intent);
        finish();
    }
}
