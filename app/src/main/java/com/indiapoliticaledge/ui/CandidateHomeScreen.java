package com.indiapoliticaledge.ui;

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
import com.indiapoliticaledge.ui.fragment.AddTestimonialFragment;
import com.indiapoliticaledge.ui.fragment.CandidateAVFragment;
import com.indiapoliticaledge.ui.fragment.CandidateDonationFragment;
import com.indiapoliticaledge.ui.fragment.CandidateProfileFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyDevFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyIssuesFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyMapFragment;
import com.indiapoliticaledge.ui.fragment.UploadManifestFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.Objects;

public class CandidateHomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    TextView toolbar_title;
    Fragment fragment = null;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candidate_flow_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String jsonObjectUser = getIntent().getStringExtra(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
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
            setTitleText("Constituency Map");
        } else if (id == R.id.constituency_devs) {
            fragment = new ConstituencyDevFragment();
            setTitleText("Constituency Development");
        } else if (id == R.id.upload_manifest) {
            fragment = new UploadManifestFragment();
            setTitleText("Upload Manifest");
        } else if (id == R.id.candidate_donation) {
            setTitleText("Candidate Donation");
            fragment = new CandidateDonationFragment();
        } else if (id == R.id.candidate_av) {
            fragment = new CandidateAVFragment();
            setTitleText("Candidate AV/Video");
        } else if (id == R.id.consti_issues) {
            fragment = new ConstituencyIssuesFragment();
            setTitleText("Constituency Issues");
        } else if (id == R.id.testimonials) {
            fragment = new AddTestimonialFragment();
            setTitleText("Testimonials");
        } else {
            fragment = new CandidateProfileFragment();
            setTitleText("Candidate Profile");
        }
        createFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USER_INFO, getIntent().getStringExtra(Constants.USER_INFO));
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
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

    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
    }


}
