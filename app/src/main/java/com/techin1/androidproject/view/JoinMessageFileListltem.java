package com.techin1.androidproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
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
public class JoinMessageFileListltem extends BaseCustomViewGroup {

    ImageView imuser;

    TextView tvnameusergroup;
    TextView tvDataPost;
    TextView tvstatus;
    TextView tvfile;
    private int ids;

    public JoinMessageFileListltem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public JoinMessageFileListltem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public JoinMessageFileListltem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public JoinMessageFileListltem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_status_file, this);
    }

    private void initInstances() {
        // findViewById here
        imuser =(ImageView) findViewById(R.id.imuser);

        tvnameusergroup = (TextView) findViewById(R.id.tvnameusergroup);
        tvDataPost = (TextView) findViewById(R.id.tvDataPost);
        tvstatus = (TextView) findViewById(R.id.tvstatus);
        tvfile = (TextView) findViewById(R.id.tvfile);



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
        int height = width * 3 / 5;
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
    public void setFileStatus(String text){

    }
    public void setFileName(String text){
        tvfile.setText(text);
    }
    public void setStatus(String text) {
        tvstatus.setText(text);
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

}
