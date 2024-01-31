package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.indiapoliticaledge.R;
import com.indiapoliticaledge.model.UserInfo;
import com.indiapoliticaledge.ui.fragment.TestimonialsFragment;
import com.indiapoliticaledge.ui.superadmin.ViewMLAListFragment;
import com.indiapoliticaledge.utils.Constants;

public class SuperAdminScreen extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    ImageView menu_icon;
    private TextView toolbar_title;
    private Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonObjectUser = getIntent().getStringExtra(Constants.USER_INFO);
        UserInfo userInfo = new Gson().fromJson(jsonObjectUser, UserInfo.class);
        Log.d("TAG", "onCreate: 1111111111111111111 "+userInfo.getPartyName());
        if (userInfo.getPartyName().equalsIgnoreCase("BJP")) {
            setTheme(R.style.Theme_IndiaPoliticalEdge_BJP);
        } else if (userInfo.getPartyName().equalsIgnoreCase("TDP")) {
            setTheme(R.style.Theme_IndiaPoliticalEdge_TDP);
        } else if (userInfo.getPartyName().equalsIgnoreCase("JANASENA")) {
            setTheme(R.style.Theme_IndiaPoliticalEdge_JANA_SENA);
        } else if (userInfo.getPartyName().equalsIgnoreCase("JANASENA")) {
            setTheme(R.style.Theme_IndiaPoliticalEdge_JANA_SENA);
        }
        setContentView(R.layout.super_admin_flow_layout);

        menu_icon = findViewById(R.id.menu_icon);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar_title = toolbar.findViewById(R.id.toolbar_title);
        setTitleText("");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        TextView user_name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        TextView user_role = navigationView.getHeaderView(0).findViewById(R.id.user_role);
        TextView user_number = navigationView.getHeaderView(0).findViewById(R.id.user_number);

        user_name.setText(userInfo.getFirstName() + " " + userInfo.getLastName());
        user_role.setText(userInfo.getRoleName());
        user_number.setText(userInfo.getMobileNumber());

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragment = new ViewMLAListFragment();
        createFragment(fragment, getIntent().getStringExtra(Constants.USER_INFO));
        setTitleText("Manage MLA's");

//        menu_icon.setOnClickListener(v -> {
//            // Initializing the popup menu and giving the reference as current context
//            PopupMenu popupMenu = new PopupMenu(ViewMLAListScreen.this, menu_icon);
//
//            // Inflating popup menu from popup_menu.xml file
//            popupMenu.getMenuInflater().inflate(R.menu.super_admin_options, popupMenu.getMenu());
//            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem menuItem) {
//                    if (menuItem.getTitle().equals("Logout")) {
//                        finish();
//                        startActivity(new Intent(ViewMLAListScreen.this, LoginScreen.class));
//                    }else if(menuItem.getTitle().equals("MLA Subscription")){
//                        startActivity(new Intent(ViewMLAListScreen.this, ManageMLASubscription.class));
//                    }else if(menuItem.getTitle().equals("Update Password")){
//                        startActivity(new Intent(ViewMLAListScreen.this, ChangePwdScreen.class));
//                    }
//                    return true;
//                }
//            });
//            // Showing the popup menu
//            popupMenu.show();
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.super_admin_options, menu);
//        return true;
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update_pwd) {
            fragment = new ChangePwdScreen();
            setTitleText("Change Password");
        } else if (id == R.id.manage_subscription) {
            fragment = new ManageMLASubscription();
            setTitleText("MLA Subscription");
        } else if (id == R.id.logout) {
            startActivity(new Intent(this, LoginScreen.class));
            finish();
            return true;
        } else if (id == R.id.testimonials) {
            setTitleText("Manage Testimonials");
            fragment = new TestimonialsFragment();
        } else if (id == R.id.forgt_pwd) {
            setTitleText("Forgot Password");
            fragment = new ForgotPwdScreen();
        } else {
            setTitleText("Manage MLA's");
            fragment = new ViewMLAListFragment();
        }
        createFragment(fragment, getIntent().getStringExtra(Constants.USER_INFO));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
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

    @Override
    public void onBackPressed() {

        if (!toolbar_title.getText().toString().equals("Manage MLA's")) {
            setTitleText("Manage MLA's");
            createFragment(new ViewMLAListFragment(), getIntent().getStringExtra(Constants.USER_INFO));
        } else {
            super.onBackPressed();
        }
    }
}
