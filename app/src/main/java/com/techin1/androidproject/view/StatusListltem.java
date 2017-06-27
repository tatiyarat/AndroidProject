package com.techin1.androidproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.StatusDao;

import java.util.Date;
import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatusListltem extends BaseCustomViewGroup {

    TextView tvnamegroup, tvstatus;
    ImageView imuser;
    ImageView imageStatus;
    TextView tvDatapost;


    public StatusListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public StatusListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public StatusListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public StatusListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status, this);
    }

    private void initInstances() {
        // findViewById here

        imuser = (ImageView) findViewById(R.id.imuser);
        tvnamegroup = (TextView) findViewById(R.id.tvnamegroup);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        imageStatus = (ImageView) findViewById(R.id.imageStatus);
        tvDatapost = (TextView) findViewById(R.id.tvDatapost);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);

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
        int height = width * 6 / 4;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        // ลูก
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // ตัวเอก
        setMeasuredDimension(width, height);
    }

    public void setNameText(String text) {
        tvnamegroup.setText(text);
    }

    public void setDatapost(String text) {
        tvDatapost.setText(text);
    }

    public void setStatus(String text) {
        if (text == null) {
            tvstatus.setVisibility(GONE);
        } else {
            tvstatus.setText(text);
        }
    }

    public void setUserStatus(String ImUserStaus) {
        Glide.with(getContext())
                .load(ImUserStaus)
                .placeholder(R.drawable.load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imuser);
    }

    public void setImageUrl(String iMstatus) {
        if (iMstatus == null) {
            imageStatus.setVisibility(GONE);
        } else {
            Glide.with(getContext())
                    .load(iMstatus)
                    .placeholder(R.drawable.loading2)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageStatus);
        }

    }

}
