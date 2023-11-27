package com.indiapoliticaledge.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref implements IPreferencesHelper {

    public static SharedPref mSharedPref;
    private SharedPreferences sharedPreferences;
    private String pushAccessToken;

    //    public static ArrayList<InventoryItem> inventoryItems;
    public static String locationText = "";

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(IPreferencesHelper.PREF_NAME, Context.MODE_PRIVATE);
    }


    public static SharedPref getmSharedPrefInstance(Context context) {
        if (mSharedPref == null)
            mSharedPref = new SharedPref(context);
        return mSharedPref;
    }


    @Override
    public void setSessionId(String sessionId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SESSION_KEY, sessionId);
        editor.apply();
    }

    @Override
    public String getSessionId() {
        return sharedPreferences.getString(SESSION_KEY, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_KEY, accessToken);
        editor.apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(ACCESS_KEY, null);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REFRESH_KEY, refreshToken);
        editor.apply();
    }

    @Override
    public String getRefreshToken() {
        return sharedPreferences.getString(REFRESH_KEY, "");
    }

    @Override
    public void removeAccessToken() {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(ACCESS_KEY).apply();
    }

    @Override
    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, userName);
        editor.apply();
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE_NUMBER, phoneNumber);
        editor.apply();
    }

    @Override
    public String getPhoneNumber() {
        return sharedPreferences.getString(PHONE_NUMBER, "");
    }

    @Override
    public void setEmailId(String emailId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_ID, emailId);
        editor.apply();
    }

    @Override
    public String getEmailId() {
        return sharedPreferences.getString(EMAIL_ID, "");
    }

    @Override
    public void setLanguage(String language) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LANGUAGE, language);
        editor.apply();
    }

    @Override
    public boolean isEnglish() {
        return sharedPreferences.getString(LANGUAGE, "English").equals("English");
    }

    @Override
    public void saveHomeAdress(String homeAddress) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HOME_ADRESS, homeAddress);
        editor.apply();
    }

    @Override
    public String getHomeAddress() {
        return sharedPreferences.getString(HOME_ADRESS, "");
    }

    @Override
    public void saveWorkAddress(String workAddress) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WORK_ADDRESS, workAddress);
        editor.apply();
    }

    @Override
    public String getWorkAddress() {
        return sharedPreferences.getString(WORK_ADDRESS, "");
    }

    @Override
    public void saveHospitalAddress(String hospitalAddress) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(HOSPITAL_ADDRESS, hospitalAddress);
        editor.apply();
    }

    @Override
    public String getHospitalAddress() {
        return sharedPreferences.getString(HOSPITAL_ADDRESS, "");
    }

    @Override
    public void saveSocialLogin(boolean isSocialLogin) {
//        IS_SOCIAL_LOGIN
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_SOCIAL_LOGIN, isSocialLogin);
        editor.apply();
    }

    @Override
    public boolean isSocialLogin() {
        return sharedPreferences.getBoolean(IS_SOCIAL_LOGIN, false);
    }

    @Override
    public void setSubscriptionType(String subscriptionType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION_TYPE, subscriptionType);
        editor.apply();
    }

    @Override
    public String getSubscriptionType() {
        return sharedPreferences.getString(SUBSCRIPTION_TYPE, "");
    }

    @Override
    public void setSubscriptionFreeDays(String subscriptionFreeDays) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION_FREE_DAYS, subscriptionFreeDays);
        editor.apply();
    }

    @Override
    public String getSubscriptionFreeDays() {
        return sharedPreferences.getString(SUBSCRIPTION_FREE_DAYS, "");
    }

    @Override
    public void setSubscriptionEndDate(String subscriptionEndDate) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION_END_DATE, subscriptionEndDate);
        editor.apply();
    }

    @Override
    public String getSubscriptionEndDate() {
        return sharedPreferences.getString(SUBSCRIPTION_END_DATE, "");
    }

    @Override
    public void setfName(String fname) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(F_NAME, fname);
        editor.apply();
    }

    @Override
    public String getFname() {
        return sharedPreferences.getString(F_NAME, "");
    }

    @Override
    public void setlName(String lName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(L_NAME, lName);
        editor.apply();
    }

    @Override
    public String getLName() {
        return sharedPreferences.getString(L_NAME, "");
    }

    @Override
    public void setEmployeeID(String employeeID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMPLOYEE_ID, employeeID);
        editor.apply();
    }

    @Override
    public String getEmployeeId() {
        return sharedPreferences.getString(EMPLOYEE_ID, "");
    }


    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }


    @Override
    public void setSubscriptionPlanType(String userPlanType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION_PLAN_TYPE, userPlanType);
        editor.apply();
    }

    @Override
    public String getSubscriptionPlanType() {
        return sharedPreferences.getString(SUBSCRIPTION_PLAN_TYPE, "");
    }

    @Override
    public void setSubscriptionRenewalDate(String nextRenewal) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION_RENEWAL_DATE, nextRenewal);
        editor.apply();
    }

    @Override
    public String getSubscriptionRenewalDate() {
        return sharedPreferences.getString(SUBSCRIPTION_RENEWAL_DATE, "");
    }

    @Override
    public void setRemeberCheck(boolean isCheked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(REMEMBER_CHECK, isCheked);
        editor.apply();
    }

    @Override
    public boolean isRemberChecked() {
        return sharedPreferences.getBoolean(REMEMBER_CHECK, false);
    }

    @Override
    public void setLoginUserName(String loginUserName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_USR_NAME, loginUserName);
        editor.apply();
    }

    @Override
    public String getLoginUsrName() {
        return sharedPreferences.getString(LOGIN_USR_NAME, "");
    }

    @Override
    public void setLoginPwd(String loginPwd) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_PWD, loginPwd);
        editor.apply();
    }

    @Override
    public String getLoginPwd() {
        return sharedPreferences.getString(LOGIN_PWD, "");
    }


}
