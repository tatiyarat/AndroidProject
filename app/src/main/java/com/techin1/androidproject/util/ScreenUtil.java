package com.techin1.androidproject.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.techin1.androidproject.manager.Contextor;


/**
 * Created by zel2__000 on 5/25/2016.
 */
public class ScreenUtil {

    private static ScreenUtil instances;

    public static ScreenUtil getInstances() {
        if (instances == null)
            instances = new ScreenUtil();
        return instances;

    }

    private Context mContext;

    private ScreenUtil() {
        mContext = Contextor.getInstances().getContext();
    }

    public int getScreenWidht(){
        WindowManager wm = (WindowManager)
                mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getScreenHeight(){
        WindowManager wm = (WindowManager)
                mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
