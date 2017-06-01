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
public class StatusNonImStatusListltem extends BaseCustomViewGroup {

    TextView tvnamegroup_non_im,tvstatus_non_im;
    TextView tvDatapost_non_im;

    ImageView imuser_non_im;

    public StatusNonImStatusListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public StatusNonImStatusListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public StatusNonImStatusListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public StatusNonImStatusListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status_nonim, this);
    }

    private void initInstances() {
        // findViewById here

        imuser_non_im = (ImageView) findViewById(R.id.imuser_non_im);

        tvnamegroup_non_im = (TextView) findViewById(R.id.tvnamegroup_non_im);
        tvstatus_non_im = (TextView) findViewById(R.id.tvstatus_non_im);
        tvDatapost_non_im = (TextView) findViewById(R.id.tvDatapost_non_im);
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
        tvnamegroup_non_im.setText(text);
    }
    public void setDataPost(String text){
        tvDatapost_non_im.setText(text);
    }
    public void setStatus(String text){
        tvstatus_non_im.setText(text);
    }
    public void setUserStatus(String ImUserStaus){
        Glide.with(getContext())
                .load(ImUserStaus)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imuser_non_im);
    }

}
