package com.techin1.androidproject.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techin1.androidproject.dao.PhotoItemCollectinDao;
import com.techin1.androidproject.dao.StatusDao;
import com.techin1.androidproject.manager.PhotoListManager;
import com.techin1.androidproject.view.JoinMessageFileListltem;
import com.techin1.androidproject.view.JoinMessageFileNonMessageListltem;
import com.techin1.androidproject.view.JoinMessageListltem;
import com.techin1.androidproject.view.JoinMessageNomMessgeListltem;
import com.techin1.androidproject.view.JoinMessageNonImListltem;
import com.techin1.androidproject.view.PhotoListItem;
import com.techin1.androidproject.view.StatusListltem;
import com.techin1.androidproject.view.StatusNonImStatusListltem;
import com.techin1.androidproject.view.StatusNonStatusListltem;

/**
 * Created by TECHIN1 on 9/11/2559.
 */

public class StatusListAdapter extends BaseAdapter {

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
    public int getViewTypeCount() {
        return 7;
//        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (dao.getData().get(position).getStatus().equals("") == true &&
                dao.getData().get(position).getJoin().equals("1") == false &&
                dao.getData().get(position).getFilestatus() != null
//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true
            ){
            return 1;
        }
        else if (
//                dao.getData().get(position).getIMstatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true &&
//                dao.getData().get(position).getJoin().equals("1") == false &&
//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true
                dao.getData().get(position).getIMstatus() == null &&
                dao.getData().get(position).getFilestatus() == null &&
                dao.getData().get(position).getJoin().equals("1") == false
                ){
            return 2;
        }
        else if (
//                dao.getData().get(position).getJoin().equals("1") == true &&
//                dao.getData().get(position).getStatus().equals("") == false &&
//                dao.getData().get(position).getStatus() != null &&
//                dao.getData().get(position).getIMstatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == false &&
//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true

                dao.getData().get(position).getJoin().equals("1") == true &&
                dao.getData().get(position).getStatus().equals("") == false &&
                dao.getData().get(position).getStatus() != null &&
                dao.getData().get(position).getIMstatus() != null &&
                dao.getData().get(position).getFilestatus() == null

                ){
            return 3;
        }else if (

//                dao.getData().get(position).getJoin().equals("1") == true &&
//                dao.getData().get(position).getStatus().equals("") == false &&
//                dao.getData().get(position).getIMstatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true &&
//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true

                dao.getData().get(position).getJoin().equals("1") == true &&
                dao.getData().get(position).getStatus().equals("") == false &&
                dao.getData().get(position).getIMstatus() == null &&
                dao.getData().get(position).getFilestatus() == null

                ){
            return 4;
        }else if (
//                dao.getData().get(position).getJoin().equals("1") == true &&
//                dao.getData().get(position).getStatus().equals("") == true &&
//                dao.getData().get(position).getStatus() != null &&
//                dao.getData().get(position).getIMstatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == false &&
//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == true

                dao.getData().get(position).getJoin().equals("1") == true &&
                dao.getData().get(position).getStatus().equals("") == true &&
                dao.getData().get(position).getStatus() != null &&
                dao.getData().get(position).getIMstatus() != null &&
                dao.getData().get(position).getFilestatus() == null
                ){
            return 5;
        }else if (

//                dao.getData().get(position).getFilestatus().equals("http://202.28.34.201/CS_Msuclub/csmsuclub/Home/Picture-messages/") == false
                dao.getData().get(position).getFilestatus() != null
                ){
            return 6;
        }
        else {
            return 0;
        }
//        return (dao.getData().get(position).getStatus().equals("") ? 0 : 1);
//        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return new StatusListltem(parent.getContext());

        View v= convertView;
        int type = getItemViewType(position);

       if (type == 2){
           Log.e("ข้อความ","2222");
           StatusNonImStatusListltem item;
           if (convertView != null)
               item = (StatusNonImStatusListltem) convertView;
           else
               item = new StatusNonImStatusListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setNameText(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setStatus(dao.getStatus());
           item.setUserStatus(dao.getIMuser());
           return item;

       }else if (type == 1){
           Log.e("ภาพ","1111");
           StatusNonStatusListltem item;
           if (convertView != null)
               item = (StatusNonStatusListltem) convertView;
           else
               item = new StatusNonStatusListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setnamegroup(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setUserStatus(dao.getIMuser());
           item.setImStatus(dao.getIMstatus());
           return item;
       }else if (type == 3){
           Log.e("จอย+ข้อความ+ภาพ","3333");
           JoinMessageListltem item;
           if (convertView != null)
               item = (JoinMessageListltem) convertView;
           else
               item = new JoinMessageListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setIDS(dao.getSid());
           item.setUserName(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setStatus(dao.getStatus());
           item.setImUset(dao.getIMuser());
           item.setImMessage(dao.getIMstatus());
           item.setDataJoin(dao.getDataremind().toString()+" "+dao.getTimeremind());

           return item;
       }else if (type == 4){
           Log.e("จอย+ภาพ","4444");
           JoinMessageNonImListltem item;
           if (convertView != null)
               item = (JoinMessageNonImListltem) convertView;
           else
               item = new JoinMessageNonImListltem(parent.getContext());
           StatusDao dao = (StatusDao) getItem(position);
           item.setUserName(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setStatus(dao.getStatus());
           item.setImUset(dao.getIMuser());
           item.setIDS(dao.getSid());
           item.setDataJoin(dao.getDataremind().toString()+" "+dao.getTimeremind());

           return item;
       }else if (type == 5){
           Log.e("จอย+ภาพ","555");
           JoinMessageNomMessgeListltem item;
           if (convertView != null)
               item = (JoinMessageNomMessgeListltem) convertView;
           else
               item = new JoinMessageNomMessgeListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setUserName(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setImUset(dao.getIMuser());
           item.setIDS(dao.getSid());
           item.setImMessage(dao.getIMstatus());
           item.setDataJoin(dao.getDataremind().toString()+" "+dao.getTimeremind());

           return item;
       }else if (type == 6){
           Log.e("ไฟล์","666");
           JoinMessageFileNonMessageListltem item;
           if (convertView != null)
               item = (JoinMessageFileNonMessageListltem) convertView;
           else
               item = new JoinMessageFileNonMessageListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setUserName(dao.getUser());
           item.setDataPost(dao.getTime());
           item.setFileStatus(dao.getFilestatus());
           item.setImUset(dao.getIMuser());
           item.setIDS(dao.getSid());
           return item;
       }else{
           Log.e("ข้อความ+ภาพ","0000");
           StatusListltem item;
           if (convertView != null)
               item = (StatusListltem) convertView;
           else
               item = new StatusListltem(parent.getContext());

           StatusDao dao = (StatusDao) getItem(position);
           item.setNameText(dao.getUser());
           item.setStatus(dao.getStatus());
           item.setDatapost(dao.getTime());
           item.setImageUrl(dao.getIMstatus());
           item.setUserStatus(dao.getIMuser());
           return item;
       }
    }
}
