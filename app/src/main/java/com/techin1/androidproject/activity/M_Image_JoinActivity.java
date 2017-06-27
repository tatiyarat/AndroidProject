package com.techin1.androidproject.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.techin1.androidproject.R;
import com.techin1.androidproject.fragment.ImageStatusFagment;
import com.techin1.androidproject.fragment.ViewpagerImageJoinFagment;
import com.techin1.androidproject.fragment.ViewpagerImageMessageFagment;


public class M_Image_JoinActivity extends AppCompatActivity implements ImageStatusFagment.FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_image_join);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_img, ViewpagerImageJoinFagment.newInstance())
                    .commit();
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initInstances() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onPhotoItemClicked() {
        // TODO: Click
    }
}
