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

import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class StatusNonStatusListltem extends BaseCustomViewGroup {

    TextView tvnamegroup_non_message;
    TextView tvDatapost_non_message;
    ImageView im_non_message;
    ImageView imuser_non_message;


    public StatusNonStatusListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public StatusNonStatusListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public StatusNonStatusListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public StatusNonStatusListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status_im_nonmessage, this);
    }

    private void initInstances() {
        // findViewById here

        imuser_non_message = (ImageView) findViewById(R.id.imuser_non_message);
        im_non_message = (ImageView) findViewById(R.id.im_non_message);

        tvnamegroup_non_message = (TextView) findViewById(R.id.tvnamegroup_non_message);
        tvDatapost_non_message = (TextView) findViewById(R.id.tvDatapost_non_message);

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
        int height = width * 4 / 3;
        int  newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        // ลูก
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // ตัวเอก
        setMeasuredDimension(width,height);
    }

    public void setnamegroup(String text){
        tvnamegroup_non_message.setText(text);
    }
    public void setDataPost(String text){
        tvDatapost_non_message.setText(text);
    }
    public void setImStatus(String ImUserStaus){
        Glide.with(getContext())
                .load(ImUserStaus)
                .placeholder(R.drawable.loading2)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(im_non_message);
    }
    public void setUserStatus(String ImUserStaus){
        Glide.with(getContext())
                .load(ImUserStaus)
                .placeholder(R.drawable.load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imuser_non_message);
    }

}
