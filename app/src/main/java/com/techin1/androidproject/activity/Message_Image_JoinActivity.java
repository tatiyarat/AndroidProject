package com.techin1.androidproject.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.techin1.androidproject.R;
import com.techin1.androidproject.adapter.ImagesListAdapter;
import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.dao.inuserjoinDao;
import com.techin1.androidproject.manager.HTTPManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Image_JoinActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    int ids;
    int idu;

    ImageView imuser;
    ImageView immessagr;

    TextView tvnamegroup;
    TextView tvDatapost;
    TextView tvstatus;

    ListView livimage;
    ImagesListAdapter listAdapter;
    TextView joinpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_image_join);

        initInstances();

        sharedPreferences = this.getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);

        sharedPreferences = this.getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        ids = sharedPreferences.getInt("idmessage", 0);

        MessageID(ids);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initInstances() {
        tvnamegroup = (TextView) findViewById(R.id.tvnamegroup);
        tvDatapost = (TextView) findViewById(R.id.tvDatapost);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        imuser = (ImageView) findViewById(R.id.imuser);
        immessagr = (ImageView) findViewById(R.id.immessagr);

        livimage = (ListView) findViewById(R.id.livimage);
        listAdapter = new ImagesListAdapter();
        livimage.setAdapter(listAdapter);

        joinpost = (TextView) findViewById(R.id.joinpost);
        joinpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJoin(idu,ids);
            }
        });


    }
    private void MessageID(int ids) {
        Call<MessageDao> call = HTTPManager.getInstances().getService().getidmessage(ids);
        call.enqueue(new Callback<MessageDao>() {
            @Override
            public void onResponse(Call<MessageDao> call, Response<MessageDao> response) {
                if (response.isSuccessful()){
                    MessageDao dao = response.body();
//                    ImageListManager.getInstances().setDao(dao);
                    listAdapter.setDao(dao);

                    listAdapter.notifyDataSetChanged();
                    tvnamegroup.setText(dao.getUser());
                    tvDatapost.setText(dao.getTime());
                    if (dao.getStatus().equals("") || dao.getStatus() == null){
                        tvstatus.setVisibility(View.GONE);
                    }
                    else {
                        tvstatus.setText(dao.getStatus());
                    }
                    Glide.with(getApplicationContext())
                        .load(dao.getIMuser())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imuser);

//                   แสดงที่อยู๋ภาพ
//                    Toast.makeText(InMessageActivity.this,""+dao.getIMstatus().get(0)
//                            , Toast.LENGTH_LONG)
//                            .show();
                }else{
                    try {
                        Toast.makeText((Message_Image_JoinActivity.this),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageDao> call, Throwable t) {
                Toast.makeText((Message_Image_JoinActivity.this),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    private void getJoin(int idu, int ids) {

        Call<inuserjoinDao> call = HTTPManager.getInstances().getService().getjoin(idu, ids);
        call.enqueue(new Callback<inuserjoinDao>() {
            @Override
            public void onResponse(Call<inuserjoinDao> call, Response<inuserjoinDao> response) {
                inuserjoinDao dao = response.body();
                if (response.isSuccessful()) {
                    if (dao.getSuccess() == 1) {
                        Toast.makeText(Message_Image_JoinActivity.this,dao.getJoin()
                                , Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(Message_Image_JoinActivity.this,dao.getJoin()
                                , Toast.LENGTH_LONG)
                                .show();
                    }

                }
            }

            @Override
            public void onFailure(Call<inuserjoinDao> call, Throwable t) {
                Toast.makeText(Message_Image_JoinActivity.this,"NO..."
                        , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
