package com.indiapoliticaledge.utils;


public interface IPreferencesHelper {
    String PREF_NAME = "hpi_pref";
    String SESSION_KEY = "session_id";
    String ACCESS_KEY = "access_token";
    String REFRESH_KEY = "refresh_token";

    String USER_NAME = "user_name";
    String PHONE_NUMBER = "phone_number";
    String EMAIL_ID = "email_id";
    String LANGUAGE = "language";

    String F_NAME = "fname";
    String L_NAME = "l_name";

    String HOME_ADRESS = "home_address";
    String WORK_ADDRESS = "work_address";
    String HOSPITAL_ADDRESS = "hospital_address";

    String LOGIN_USR_NAME = "login_user_name";
    String LOGIN_PWD = "login_password";

    String REMEMBER_CHECK = "remember_check";
    String IS_SOCIAL_LOGIN = "is_social_login";

    String PUSH_NOTIFICATION_ID = "push_token";
    String SUBSCRIPTION_TYPE = "subscription_type";
    String SUBSCRIPTION_FREE_DAYS = "subscription_days";
    String SUBSCRIPTION_END_DATE = "subscription_end_date";

    String SUBSCRIPTION_PLAN_TYPE = "userPlanType";
    String SUBSCRIPTION_RENEWAL_DATE = "nextRenewal";
    String EMPLOYEE_ID="employee_id";
    String INVENTORY_LIST="INVENTORY_LIST";
    String LOCATION_CODE="LOCATION_CODE";


    void setSubscriptionPlanType(String userPlanType);

    String getSubscriptionPlanType();

    void setSubscriptionRenewalDate(String nextRenewal);

    String getSubscriptionRenewalDate();

    void setRemeberCheck(boolean isCheked);

    boolean isRemberChecked();

    void setLoginUserName(String loginUserName);

    String getLoginUsrName();

    void setLoginPwd(String loginPwd);

    String getLoginPwd();


    void setSessionId(String sessionId);

    String getSessionId();

    void setAccessToken(String accessToken);

    String getAccessToken();


    void setRefreshToken(String refreshToken);

    String getRefreshToken();

    void removeAccessToken();

    void setUserName(String userName);

    String getUserName();

    void setPhoneNumber(String phoneNumber);

    String getPhoneNumber();


    void setEmailId(String emailId);

    String getEmailId();

    void setLanguage(String language);

    boolean isEnglish();

    void saveHomeAdress(String homeAddress);

    String getHomeAddress();

    void saveWorkAddress(String workAddress);

    String getWorkAddress();

    void saveHospitalAddress(String hospitalAddress);

    String getHospitalAddress();

    void saveSocialLogin(boolean isSocialLogin);

    boolean isSocialLogin();

    void setSubscriptionType(String subscriptionType);

    String getSubscriptionType();

    void setSubscriptionFreeDays(String subscriptionFreeDays);

    String getSubscriptionFreeDays();

    void setSubscriptionEndDate(String subscriptionEndDate);

    String getSubscriptionEndDate();

    void setfName(String fname);

    String getFname();

    void setlName(String lName);

    String getLName();

    void setEmployeeID(String employeeID);
    String getEmployeeId();



}