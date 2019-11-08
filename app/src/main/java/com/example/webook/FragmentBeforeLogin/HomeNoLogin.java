package com.example.webook.FragmentBeforeLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.webook.LoginActivity;
import com.example.webook.R;
import com.google.android.material.navigation.NavigationView;

public class HomeNoLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nologin);
        Toolbar toolbar = findViewById(R.id.toolbar_nologin);
        setSupportActionBar(toolbar);

//        //initiate drawer
        drawerLayout = findViewById(R.id.drawer_layout_nologin);
        NavigationView navigationView = findViewById(R.id.nav_view_noLogin);
        navigationView.setNavigationItemSelectedListener(this);
//
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentNoLogin, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.navigation_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_sigin:
                Intent login = new Intent(HomeNoLogin.this, LoginActivity.class);
                finish();
                startActivity(login);
                break;
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentNoLogin, new HomeFragment()).commit();
                break;
            case R.id.navigation_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentNoLogin, new SettingNoLoginFragment()).commit();
                break;
            case R.id.navigation_exit:
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
