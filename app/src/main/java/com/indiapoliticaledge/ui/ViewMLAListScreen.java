package com.indiapoliticaledge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
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
import com.indiapoliticaledge.ui.superadmin.ViewMLAListFragment;
import com.indiapoliticaledge.utils.Constants;

public class ViewMLAListScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ImageView menu_icon;
    private TextView toolbar_title;
    private Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_admin_flow_layout);


        menu_icon = findViewById(R.id.menu_icon);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        fragment = new ViewMLAListFragment();
        createFragment();


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
        } else if (id == R.id.manage_subscription) {
            fragment = new ManageMLASubscription();
        } else if (id == R.id.logout) {
            finish();
            startActivity(new Intent(this, LoginScreen.class));
        } else {
            fragment = new ViewMLAListFragment();
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
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commitAllowingStateLoss();
    }

    public void setTitleText(String titleText) {
        toolbar_title.setText(titleText);
    }
}
