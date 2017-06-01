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

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HomeListltem extends BaseCustomViewGroup {

    TextView tvnamegroup,tvstatus;
    ImageView imuser;
    ImageView imageStatus;

    public HomeListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public HomeListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public HomeListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public HomeListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.fragment_home, this);
    }

    private void initInstances() {
        // findViewById here

        imuser = (ImageView) findViewById(R.id.imuser);
        tvnamegroup = (TextView) findViewById(R.id.tvnamegroup);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        imageStatus  = (ImageView) findViewById(R.id.imageStatus);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
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
        int height = width * 2 / 3;
        int  newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        // ลูก
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // ตัวเอก
        setMeasuredDimension(width,height);
    }

    public void setNameText(String text){
        tvnamegroup.setText(text);
    }
    public void setStatus(String text){
        tvstatus.setText(text);
    }
    public void setUserStatus(String ImUserStaus){
        Glide.with(getContext())
                .load(ImUserStaus)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imuser);
    }
    public void setImageUrl(String iMstatus){
        Glide.with(getContext())
                .load(iMstatus)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageStatus);
//                .placeholder()
//                .error()
//วงกลม                .transform()


    }
}
