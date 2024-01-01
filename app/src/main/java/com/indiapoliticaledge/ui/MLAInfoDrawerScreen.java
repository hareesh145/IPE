package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
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
import com.indiapoliticaledge.ui.fragment.ManageCandidatesFragment;
import com.indiapoliticaledge.ui.fragment.UploadManifestFragment;
import com.indiapoliticaledge.utils.Constants;

public class MLAInfoDrawerScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView toolbar_title;
    Fragment fragment = null;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.admin_flow_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        String jsonObjectUser = getIntent().getStringExtra(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        TextView user_name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView user_role = navigationView.getHeaderView(0).findViewById(R.id.user_role);
        TextView user_number = navigationView.getHeaderView(0).findViewById(R.id.user_number);

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

    public void createFragment(Fragment fragment, String userInfo) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USER_INFO, userInfo);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.manage_candidates) {
            fragment = new ManageCandidatesFragment();
            setTitleText(getString(R.string.manage_candidates));
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
        } else if (id == R.id.candidate_av) {
            fragment = new CandidateAVFragment();
            setTitleText(getString(R.string.upload_av));
        } else if (id == R.id.view_candidate_donations) {
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
        } else if (id == R.id.opinion_polling) {
            fragment = new OpinionPollingFragment();
            setTitleText(getString(R.string.opinion_polling));
        } else if (id == R.id.testimonials) {
            fragment = new AddTestimonialFragment();
            setTitleText(getString(R.string.testimonials));
        } else if (id == R.id.upload_images) {
            fragment = new UploadImagesFragment();
            setTitleText(getString(R.string.upload_images));
        } else if (id == R.id.social_media_reviews) {
            fragment = new SocialMediaFragment();
            setTitleText(getString(R.string.social_media_reviews));
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
