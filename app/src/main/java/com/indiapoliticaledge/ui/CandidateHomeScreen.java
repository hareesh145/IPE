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
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.ui.fragment.CandidateAVFragment;
import com.indiapoliticaledge.ui.fragment.CandidateDonationFragment;
import com.indiapoliticaledge.ui.fragment.CandidateProfileFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyDevFragment;
import com.indiapoliticaledge.ui.fragment.ConstituencyMapFragment;
import com.indiapoliticaledge.ui.fragment.UploadManifestFragment;
import com.indiapoliticaledge.utils.Constants;

import java.util.Objects;

public class CandidateHomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    TextView toolbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candidate_flow_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        setTitleText("");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.constituency_map) {
            fragment = new ConstituencyMapFragment();
        } else if (id == R.id.constituency_devs) {
            fragment = new ConstituencyDevFragment();
        } else if (id == R.id.upload_manifest) {
            fragment = new UploadManifestFragment();
        } else if (id == R.id.candidate_donation) {
            fragment = new CandidateDonationFragment();
        } else if (id == R.id.candidate_av) {
            fragment = new CandidateAVFragment();
        } else {
            fragment = new CandidateProfileFragment();
        }
        Bundle bundle=new Bundle();
        bundle.putString(Constants.USER_INFO,getIntent().getStringExtra(Constants.USER_INFO));
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
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

    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
    }


}
