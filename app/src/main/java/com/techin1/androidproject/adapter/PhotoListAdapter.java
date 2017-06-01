package com.techin1.androidproject.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techin1.androidproject.manager.PhotoListManager;
import com.techin1.androidproject.view.PhotoListItem;

/**
 * Created by TECHIN1 on 25/10/2559.
 */

public class PhotoListAdapter extends BaseAdapter {


    @Override
    public int getCount() {
//        if (PhotoListManager.getInstances().getIduser() == 0)
//            return 0;
//        if (PhotoListManager.getInstances().getIduser() == 0)
//            return 0;
//        return PhotoListManager.getInstances().getIduser();
        return 100;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position %2 == 0 ? 0 : 1;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotoListItem item;
        if (convertView != null)
            item = (PhotoListItem) convertView;
        else
            item = new PhotoListItem(parent.getContext());
        return item;

    }
}
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (getItemViewType(position) == 0){
//            PhotoListItem item;
//            if (convertView != null)
//                item = (PhotoListItem) convertView;
//            else
//                item = new PhotoListItem(parent.getContext());
//            return item;
//        }
//        else {
//            TextView item;
//            if (convertView != null)
//                item = (TextView) convertView;
//            else
//                item = new TextView(parent.getContext());
//            item.setText("Positlion: " + position);
//            return item;
//        }
//
//    }

