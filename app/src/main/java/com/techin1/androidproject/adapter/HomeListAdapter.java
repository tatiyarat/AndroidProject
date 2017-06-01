package com.techin1.androidproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techin1.androidproject.dao.PhotoItemCollectinDao;
import com.techin1.androidproject.dao.StatusDao;
import com.techin1.androidproject.view.StatusListltem;

/**
 * Created by TECHIN1 on 9/11/2559.
 */

public class HomeListAdapter extends BaseAdapter {

    PhotoItemCollectinDao dao;

    public void setDao(PhotoItemCollectinDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        return dao.getData().size();

//        if (PhotoListManager.getInstances().getDao() == null)
//            return 0;
//        if (PhotoListManager.getInstances().getDao().getData() == null)
//            return 0;
//        return PhotoListManager.getInstances().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StatusListltem item;
        if (convertView != null)
            item = (StatusListltem) convertView;
        else
            item = new StatusListltem(parent.getContext());

        StatusDao dao = (StatusDao) getItem(position);
        item.setNameText(dao.getUser());
        item.setStatus(dao.getStatus());
        item.setImageUrl(dao.getIMstatus());
        item.setUserStatus(dao.getIMuser());
        return item;
    }
}
