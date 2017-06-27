package com.techin1.androidproject.manager;

import android.content.Context;

import com.techin1.androidproject.dao.MessageDao;

/**
 * Created by zel2__000 on 5/25/2016.
 */
public class ImageListManager {

    private static ImageListManager instances;

    public static ImageListManager getInstances() {
        if (instances == null)
            instances = new ImageListManager();
        return instances;

    }

    private Context mContext;
    private MessageDao dao;

    private ImageListManager() {
        mContext = Contextor.getInstances().getContext();
    }

    public MessageDao getDao() {
        return dao;
    }

    public void setDao(MessageDao dao) {
        this.dao = dao;
    }
}
