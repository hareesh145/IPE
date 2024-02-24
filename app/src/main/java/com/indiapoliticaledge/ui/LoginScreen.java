package com.indiapoliticaledge.ui;

import static com.indiapoliticaledge.utils.Constants.CONSTITUENCY_IMAGES;
import static com.indiapoliticaledge.utils.Constants.CONSTITUENCY_VIDEOS;
import static com.indiapoliticaledge.utils.Constants.LOGIN_RESPONSE;
import static com.indiapoliticaledge.utils.Constants.MLA_INFO;
import static com.indiapoliticaledge.utils.Constants.MY_IMAGES;
import static com.indiapoliticaledge.utils.Constants.MY_VIDEOS;
import static com.indiapoliticaledge.utils.Constants.NOTICE_MESSAGES;
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
import com.indiapoliticaledge.utils.SharedPref;
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
                        //navigateHome(null);
                        Utils.showSnackBarAlert(submit_btn, "We're sorry ! The server has encountered an internal error !!! Please try again later.");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
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
        SharedPref.getmSharedPrefInstance(this).saveString(LOGIN_RESPONSE, new Gson().toJson(signInResponseModel));
        SharedPref.getmSharedPrefInstance(this).saveString(NOTICE_MESSAGES, new Gson().toJson(signInResponseModel.noticeMessagesList));
        SharedPref.getmSharedPrefInstance(this).saveString(MY_IMAGES, new Gson().toJson(signInResponseModel.myImages));
        SharedPref.getmSharedPrefInstance(this).saveString(MY_VIDEOS, new Gson().toJson(signInResponseModel.myAVs));
        SharedPref.getmSharedPrefInstance(this).saveString(CONSTITUENCY_IMAGES, new Gson().toJson(signInResponseModel.constituencyImages));
        SharedPref.getmSharedPrefInstance(this).saveString(CONSTITUENCY_VIDEOS, new Gson().toJson(signInResponseModel.constituencyAVs));

        Intent refresh = new Intent(this, MLAInfoDrawerScreen.class);
        if (signInResponseModel != null && signInResponseModel.getUserInfo() != null) {
            refresh.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
            startActivity(refresh);
        }
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
        SharedPref.getmSharedPrefInstance(this).saveString(LOGIN_RESPONSE, new Gson().toJson(signInResponseModel));
        SharedPref.getmSharedPrefInstance(this).saveString(NOTICE_MESSAGES, new Gson().toJson(signInResponseModel.noticeMessagesList));
        SharedPref.getmSharedPrefInstance(this).saveString(MY_IMAGES, new Gson().toJson(signInResponseModel.myImages));
        SharedPref.getmSharedPrefInstance(this).saveString(MY_VIDEOS, new Gson().toJson(signInResponseModel.myAVs));
        SharedPref.getmSharedPrefInstance(this).saveString(CONSTITUENCY_IMAGES, new Gson().toJson(signInResponseModel.constituencyImages));
        SharedPref.getmSharedPrefInstance(this).saveString(CONSTITUENCY_VIDEOS, new Gson().toJson(signInResponseModel.constituencyAVs));
        SharedPref.getmSharedPrefInstance(this).saveString(MLA_INFO, new Gson().toJson(signInResponseModel.mlaInfo));

        Intent intent = new Intent(LoginScreen.this, CandidateHomeScreen.class);
        if (signInResponseModel != null && signInResponseModel.getUserInfo() != null) {
            intent.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
            startActivity(intent);
        }
        finish();
    }

    private void navigateHome(SignInResponseModel signInResponseModel) {
        Intent intent = new Intent(LoginScreen.this, SuperAdminScreen.class);
        if (signInResponseModel != null && signInResponseModel.getUserInfo() != null) {
            intent.putExtra(USER_INFO, new Gson().toJson(signInResponseModel.getUserInfo()));
            startActivity(intent);
        }
        finish();
    }
}
