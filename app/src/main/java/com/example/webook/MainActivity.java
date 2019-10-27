package com.example.webook;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String s = null;
        if (bundle != null) {
            s = bundle.getString("username");
        }
        Toast.makeText(this, "welcome, "+s, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        //toolbar for drawer navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initiate drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NotesFragment()).commit();
            navigationView.setCheckedItem(R.id.navigation_notes);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_notes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new NotesFragment()).commit();
                break;
            case R.id.navigation_url:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new UrlFragment()).commit();
                break;
            case R.id.navigation_todo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new TodoFragment()).commit();
                break;
            case R.id.navigation_image:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ImageFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
