package com.techin1.androidproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.inuserjoinDao;
import com.techin1.androidproject.manager.HTTPManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class JoinMessageNonImListltem extends BaseCustomViewGroup {

    ImageView imuser;

    TextView tvnameusergroup;
    TextView tvDataPost;
    TextView tvstatus;

    TextView joinpost;
    TextView tvDataJoin;
    private int ids;
    private SharedPreferences sharedPreferences;
    private int idu;


    public JoinMessageNonImListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public JoinMessageNonImListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public JoinMessageNonImListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public JoinMessageNonImListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status_join_gost_non_im, this);
    }

    private void initInstances() {
        // findViewById here

        sharedPreferences = getContext().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        idu = sharedPreferences.getInt("iduser", 0);

        imuser =(ImageView) findViewById(R.id.imuser);

        tvnameusergroup = (TextView) findViewById(R.id.tvnameusergroup);
        tvDataPost = (TextView) findViewById(R.id.tvDataPost);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        joinpost = (TextView) findViewById(R.id.joinpost);
        tvDataJoin = (TextView) findViewById(R.id.tvDataJoin);

        joinpost.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle("ยืนยันการเข้าร่วม");
                alert.setMessage("ยืนยันการเข้าร่วม");
                alert.setIcon(R.drawable.confirm);

                alert.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getJoin(idu, ids);
                    }
                });

                alert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.create();

                alert.show();
            }
        });


    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec); // ไดเความกว้าง
        int height = width * 2 / 2;
        int  newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        // ลูก
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // ตัวเอก
        setMeasuredDimension(width,height);
    }

    public void setUserName(String text)
    {
        tvnameusergroup.setText(text);
    }
    public void setDataPost(String text){
        tvDataPost.setText(text);
    }
    public void setStatus(String text) {
        tvstatus.setText(text);
    }
    public void setDataJoin(String text){
        tvDataJoin.setText(text);
    }
    public void setIDS(int text) {
        ids = text;
    }

    public void setImUset(String ImUser){
        Glide.with(getContext())
                .load(ImUser)
                .placeholder(R.drawable.load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imuser);
    }
    private void getJoin(final int idu, final int ids) {

        Call<inuserjoinDao> call = HTTPManager.getInstances().getService().getjoin(idu, ids);
        call.enqueue(new Callback<inuserjoinDao>() {
            @Override
            public void onResponse(Call<inuserjoinDao> call, Response<inuserjoinDao> response) {
                inuserjoinDao dao = response.body();
                if (response.isSuccessful()) {
                    if (dao.getSuccess() == 1) {
                        Toast.makeText(getContext(), dao.getJoin(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), dao.getJoin(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<inuserjoinDao> call, Throwable t) {

                Toast.makeText(getContext(), "NO...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
