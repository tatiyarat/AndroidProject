package com.techin1.androidproject.manager;

import android.content.Context;

import com.techin1.androidproject.dao.Groups;
import com.techin1.androidproject.dao.PhotoItemCollectinDao;

/**
 * Created by zel2__000 on 5/25/2016.
 */
public class PhotoListManager {

    private static PhotoListManager instances;

    public static PhotoListManager getInstances() {
        if (instances == null)
            instances = new PhotoListManager();
        return instances;

    }

    private Context mContext;
    private PhotoItemCollectinDao dao;

    public PhotoListManager() {
        mContext = Contextor.getInstances().getContext();
    }

    public PhotoItemCollectinDao getDao() {
        return dao;
    }

    public void setDao(PhotoItemCollectinDao dao) {
        this.dao = dao;
    }
}
