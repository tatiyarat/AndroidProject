package com.techin1.androidproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.manager.ImageListManager;
import com.techin1.androidproject.view.ImagesListltem;

/**
 * Created by techin on 20/6/2560.
 */

public class ImagesListAdapter extends BaseAdapter{

    MessageDao dao;

    public void setDao(MessageDao dao){
        this.dao = dao;
    }

    @Override
    public int getCount() {
//        return 10;
        if (dao == null)
            return 0;
        if (dao.getIMstatus() == null)
            return 0;
        return dao.getIMstatus().size();
    }

    @Override
    public Object getItem(int position) {
        return dao ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ImagesListltem item;
        if (convertView != null)
            item = (ImagesListltem) convertView;
        else
            item = new ImagesListltem(parent.getContext());

        MessageDao dao = (MessageDao) getItem(position);
        item.serImageUrl(dao.getIMstatus().get(position));

        return item;
    }
}
