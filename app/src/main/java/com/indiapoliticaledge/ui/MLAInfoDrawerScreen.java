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

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.databinding.AdminFlowLayoutBinding;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.admin.BoothPoolManagementFragment;
import com.indiapoliticaledge.ui.admin.ConstituencyPoolManagementFragment;
import com.indiapoliticaledge.ui.admin.ContactUsFragment;
import com.indiapoliticaledge.ui.admin.LatestNewsFragment;
import com.indiapoliticaledge.ui.admin.ManageDevlopmentFragment;
import com.indiapoliticaledge.ui.admin.NoticeBoardMessagesFragment;
import com.indiapoliticaledge.ui.admin.OpinionPollingFragment;
import com.indiapoliticaledge.ui.admin.SocialMediaFragment;
import com.indiapoliticaledge.ui.admin.UploadImagesFragment;
import com.indiapoliticaledge.ui.admin.ViewCandidateDonationsFrgment;
import com.indiapoliticaledge.ui.admin.ViewCandidateRatingFragment;
import com.indiapoliticaledge.ui.admin.ViewConstituencyIssuesFragment;
import com.indiapoliticaledge.ui.fragment.AddTestimonialFragment;
import com.indiapoliticaledge.ui.fragment.CandidateAVFragment;
import com.indiapoliticaledge.ui.fragment.CandidateDonationFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyDevFragment;
import com.indiapoliticaledge.ui.fragment.MLAInfoFragment;
import com.indiapoliticaledge.ui.fragment.ManageVotersFragment;
import com.indiapoliticaledge.ui.fragment.UploadManifestFragment;
import com.indiapoliticaledge.utils.Constants;

public class MLAInfoDrawerScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView toolbar_title;
    Fragment fragment = null;

    private DrawerLayout drawer;

    AdminFlowLayoutBinding binding;
    private Toolbar toolbar;

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
            } else if (userInfo.getPartyName().equalsIgnoreCase("CONGRESS")) {
                setTheme(R.style.Theme_IndiaPoliticalEdge_CONGRESS);
            } else if (userInfo.getPartyName().equalsIgnoreCase("YSRCP")) {
                setTheme(R.style.Theme_IndiaPoliticalEdge_YSRCP);
            } else {
                setTheme(R.style.Theme_IndiaPoliticalEdge);
            }
        } else {
            setTheme(R.style.Theme_IndiaPoliticalEdge);
        }
        binding = AdminFlowLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        TextView user_name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView user_role = navigationView.getHeaderView(0).findViewById(R.id.user_role);
        TextView user_number = navigationView.getHeaderView(0).findViewById(R.id.user_number);

        updateBGColors(userInfo);

        user_name.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
        user_role.setText(getString(R.string.admin));
        user_number.setText(userInfo.getMobileNumber());


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragment = new MLAInfoFragment();
        setTitleText(getString(R.string.admin_profile));
        createFragment(fragment, getIntent().getStringExtra(Constants.USER_INFO));
    }

    private void updateBGColors(UserInfo userInfo) {
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
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.manage_candidates) {
            fragment = new ManageVotersFragment();
            setTitleText(getString(R.string.manage_voters));
        } else if (id == R.id.notice_board_messages) {
            fragment = new NoticeBoardMessagesFragment();
            setTitleText(getString(R.string.notice_board_messages));
        } else if (id == R.id.constituency_devs) {
            fragment = new ConstituencyDevFragment();
            setTitleText(getString(R.string.constituency_development));
        } else if (id == R.id.upload_manifest) {
            fragment = new UploadManifestFragment();
            setTitleText(getString(R.string.upload_manifesto));
        } else if (id == R.id.manage_development) {
            fragment = new ManageDevlopmentFragment();
            setTitleText(getString(R.string.manage_development));
        } else if (id == R.id.candidate_donation) {
            fragment = new CandidateDonationFragment();
            setTitleText(getString(R.string.view_candidate_donates));
        }
        /*else if (id == R.id.candidate_av) {
            fragment = new CandidateAVFragment();
            setTitleText(getString(R.string.upload_av));
        }*/
        else if (id == R.id.view_candidate_donations) {
            fragment = new ViewCandidateDonationsFrgment();
            setTitleText(getString(R.string.view_candidate_donates));
        } else if (id == R.id.consti_issues) {
            fragment = new ViewConstituencyIssuesFragment();
            setTitleText(getString(R.string.constituency_issues));
        } else if (id == R.id.leader_rating) {
            fragment = new ViewCandidateRatingFragment();
            setTitleText(getString(R.string.view_candiates_rating));
        } else if (id == R.id.latest_news) {
            fragment = new LatestNewsFragment();
            setTitleText(getString(R.string.latest_news));
        } else if (id == R.id.opinion_polling) {
            fragment = new OpinionPollingFragment();
            setTitleText(getString(R.string.opinion_polling));
        } else if (id == R.id.testimonials) {
            fragment = new AddTestimonialFragment();
            setTitleText(getString(R.string.testimonials));
        } else if (id == R.id.upload_images) {
            fragment = new UploadImagesFragment();
            setTitleText(getString(R.string.upload_image_av));
        } else if (id == R.id.social_media_reviews) {
            fragment = new SocialMediaFragment();
            setTitleText(getString(R.string.social_media_reviews));
        } else if (id == R.id.booth_level_management) {
            fragment = new BoothPoolManagementFragment();
            setTitleText(getString(R.string.booth_level_managment));
        } else if (id == R.id.pool_management) {
            fragment = new ConstituencyPoolManagementFragment();
            setTitleText(getString(R.string.constituency_poll_managment));
        } else if (id == R.id.contact_us) {
            fragment = new ContactUsFragment();
            setTitleText(getString(R.string.contact_us));
        } else if (id == R.id.change_password_info) {
            fragment = new ChangePwdScreen();
            setTitleText(getString(R.string.change_password));
        } else if (id == R.id.logout) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        } else {
            fragment = new MLAInfoFragment();
            setTitleText(getString(R.string.admin_profile));
        }
        createFragment(fragment, getIntent().getStringExtra(Constants.USER_INFO));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!toolbar_title.getText().toString().equals(getString(R.string.admin_profile))) {
                setTitleText(getString(R.string.admin_profile));
                createFragment(new MLAInfoFragment(), getIntent().getStringExtra(Constants.USER_INFO));
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
    }

}
