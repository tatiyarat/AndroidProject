package com.techin1.androidproject.manager;

import android.content.Context;

/**
 * Created by zel2__000 on 5/25/2016.
 */
public class Contextor {
    private static Contextor instances;

    public static Contextor getInstances() {
        if (instances == null)
            instances = new Contextor();
        return instances;
    }

    private Context mContext;
    private Contextor(){}

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext(){
        return mContext;}
}
