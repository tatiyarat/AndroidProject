package com.techin1.androidproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techin1.androidproject.dao.GroupJoinDataDao;
import com.techin1.androidproject.dao.GroupsJoinDao;
import com.techin1.androidproject.dao.MessageDao;
import com.techin1.androidproject.view.GroupJoinListItem;
import com.techin1.androidproject.view.ImagesListltem;

/**
 * Created by techin on 20/6/2560.
 */

public class GroupJoinListAdapter extends BaseAdapter{

    GroupJoinDataDao dao;

    public void setDao(GroupJoinDataDao dao){
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getData() == null)
            return 0;
        return dao.getData().size();
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
       GroupJoinListItem item;
        if (convertView != null)
            item = (GroupJoinListItem) convertView;
        else
            item = new GroupJoinListItem(parent.getContext());

        GroupJoinDataDao dao = (GroupJoinDataDao) getItem(position);

        item.serImageUrl(dao.getData().get(position).getDesign());
        item.setNameGroup(dao.getData().get(position).getGname());

        return item;
    }
}
