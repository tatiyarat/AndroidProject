package com.techin1.androidproject.manager;

import android.content.Context;

/**
 * Created by zel2__000 on 5/25/2016.
 */
public class SingletonTemplate {

    private static SingletonTemplate instances;

    public static SingletonTemplate getInstances() {
        if (instances == null)
            instances = new SingletonTemplate();
        return instances;

    }

    private Context mContext;

    private SingletonTemplate() {
        mContext = Contextor.getInstances().getContext();
    }
}
