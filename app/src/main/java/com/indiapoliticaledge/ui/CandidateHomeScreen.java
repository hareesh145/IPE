package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.CandidateFlowLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.admin.ContactUsFragment;
import com.indiapoliticaledge.ui.admin.LatestNewsFragment;
import com.indiapoliticaledge.ui.admin.OpinionPollingFragment;
import com.indiapoliticaledge.ui.admin.SocialMediaFragment;
import com.indiapoliticaledge.ui.admin.UploadImagesFragment;
import com.indiapoliticaledge.ui.admin.ViewConstituencyIssuesFragment;
import com.indiapoliticaledge.ui.fragment.AddTestimonialFragment;
import com.indiapoliticaledge.ui.fragment.CandidateAVFragment;
import com.indiapoliticaledge.ui.fragment.CandidateDonationFragment;
import com.indiapoliticaledge.ui.fragment.CandidateProfileFragment;
import com.indiapoliticaledge.ui.fragment.CandidateRatingFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyDevFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyMapFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.Objects;

public class CandidateHomeScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    TextView toolbar_title;
    Fragment fragment = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private Toolbar toolbar;

    CandidateFlowLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonObjectUser = getIntent().getStringExtra(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        Log.d("TAG", "onCreate: 1111111111111111111 " + userInfo.getPartyName());
        if (userInfo != null && userInfo.getPartyName() != null) {
            if (userInfo.getPartyName().equalsIgnoreCase("BJP")) {
                setTheme(R.style.Theme_IndiaPoliticalEdge_BJP);
            } else if (userInfo.getPartyName().equalsIgnoreCase("TDP")) {
                setTheme(R.style.Theme_IndiaPoliticalEdge_TDP);
            } else if (userInfo.getPartyName().equalsIgnoreCase("JANASENA")) {
                setTheme(R.style.Theme_IndiaPoliticalEdge_JANA_SENA);
            } else {
                setTheme(R.style.Theme_IndiaPoliticalEdge);
            }
        } else {
            setTheme(R.style.Theme_IndiaPoliticalEdge);
        }
        binding = CandidateFlowLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);

        updateBGColors(userInfo);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView user_name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView user_role = navigationView.getHeaderView(0).findViewById(R.id.user_role);
        TextView user_number = navigationView.getHeaderView(0).findViewById(R.id.user_number);

        user_name.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
        user_role.setText(getString(R.string.candidate));
        user_number.setText(userInfo.getMobileNumber());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragment = new CandidateProfileFragment();
        setTitleText(getString(R.string.candidate_profile));
        createFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.constituency_map) {
            fragment = new ConstituencyMapFragment();
            setTitleText(getString(R.string.constituency_map));
        } else if (id == R.id.constituency_devs) {
            fragment = new ConstituencyDevFragment();
            setTitleText(getString(R.string.candidate_constituency_development));
        } else if (id == R.id.candidate_donation) {
            setTitleText(getString(R.string.candidate_donates));
            fragment = new CandidateDonationFragment();
        } else if (id == R.id.candidate_av) {
            fragment = new CandidateAVFragment();
            setTitleText(getString(R.string.candidate_upload_av));
        } else if (id == R.id.consti_issues) {
            fragment = new ViewConstituencyIssuesFragment();
            setTitleText(getString(R.string.candidate_constituency_issues));
        } else if (id == R.id.upload_images_photos) {
            fragment = new UploadImagesFragment();
            setTitleText(getString(R.string.candidate_upload_images));
        } else if (id == R.id.latest_news) {
            fragment = new LatestNewsFragment();
            setTitleText(getString(R.string.latest_news));
        } else if (id == R.id.opinion_polling) {
            fragment = new OpinionPollingFragment();
            setTitleText(getString(R.string.opinion_polling));
        } else if (id == R.id.testimonials) {
            fragment = new AddTestimonialFragment();
            setTitleText(getString(R.string.candidate_testimonials));
        } else if (id == R.id.social_reviews) {
            fragment = new SocialMediaFragment();
            setTitleText(getString(R.string.social_media_reviews));
        } else if (id == R.id.contact_us) {
            fragment = new ContactUsFragment();
            setTitleText(getString(R.string.contact_us));
        } else if (id == R.id.change_pwd) {
            fragment = new ChangePwdScreen();
            setTitleText(getString(R.string.change_password));
        } else if (id == R.id.leader_rating) {
            fragment = new CandidateRatingFragment();
            setTitleText(getString(R.string.constituency_leader_rating));
        } else if (id == R.id.logout) {
            finishAffinity();
            startActivity(new Intent(this, LoginScreen.class));
            return true;
        } else {
            fragment = new CandidateProfileFragment();
            setTitleText(getString(R.string.candidate_profile_info));
        }
        createFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void updateBGColors(UserInfo userInfo) {
        if (userInfo.getPartyName() != null) {
            if (userInfo.getPartyName().equals("BJP")) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.bjp_color));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.bjp_color));
            } else if (userInfo.getPartyName().equalsIgnoreCase("JANASENA")) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.jana_sena_color));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.jana_sena_color));
            } else if (userInfo.getPartyName().equals("TDP")) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.tdp_color));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.tdp_color));
            } else if (userInfo.getPartyName().equals("CONGRESS")) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.congress_color_header));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.congress_color_header));
            } else if (userInfo.getPartyName().equals("YSRCP")) {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.ysrcp_color_header));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.ysrcp_color_header));
            } else {
                toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_gradient_start));
                View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
                rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_gradient_start));
            }
        } else {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_gradient_start));
            View rootView = binding.navView.getHeaderView(0).findViewById(R.id.header_linear_view);
            rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_gradient_start));
        }
    }

    private void createFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USER_INFO, getIntent().getStringExtra(Constants.USER_INFO));
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
    }


}
