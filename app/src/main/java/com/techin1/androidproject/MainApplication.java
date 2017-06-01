package com.techin1.androidproject;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by TECHIN1 on 10/14/2016.
 */

public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        // Intiali
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
