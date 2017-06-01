package com.techin1.androidproject.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.techin1.androidproject.GroupActivity;
import com.techin1.androidproject.LoginActivity;
import com.techin1.androidproject.R;
import com.techin1.androidproject.Session;
import com.techin1.androidproject.dao.Home;
import com.techin1.androidproject.fragment.GoupMenuFragment;
import com.techin1.androidproject.fragment.HomeFragment;
import com.techin1.androidproject.fragment.MainFragment;
import com.techin1.androidproject.fragment.UpFragment;
import com.techin1.androidproject.manager.HTTPManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Session session;
    private TextView tvNameUserGroup,textView;
    private SharedPreferences sharedPreferences;
    private int idu;

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_group);


        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.contentContainer, GoupMenuFragment.newInstance())
//                    .commit();
//        }

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvNameUserGroup = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tvNameUserGroup);
        textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView);

        sharedPreferences = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser",0);
        gethomeuser(idu);

        init();

    }

    private void init() {
        session = new Session(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            UpFragment fragment = new UpFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MenuGroupActivity.this,LoginActivity.class));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            HomeFragment fragment = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {
            GoupMenuFragment fragment = new GoupMenuFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            Toast.makeText(this, "Group", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_send) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gethomeuser(int idu) {

        Call<Home> call = HTTPManager.getInstances().getService().getNameUser(idu);
        call.enqueue(new Callback<Home>() {

            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                Home dao = response.body();
                if (response.isSuccessful()) {
                    tvNameUserGroup.setText(dao.getName());
                    textView.setText(dao.getIduser());
                }
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                Toast.makeText(MenuGroupActivity.this,"NO..."
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}