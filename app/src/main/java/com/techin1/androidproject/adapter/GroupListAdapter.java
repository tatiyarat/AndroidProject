package com.techin1.androidproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techin1.androidproject.GroupMenuName;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.Groups;
import com.techin1.androidproject.view.GroupListItem;
import com.techin1.androidproject.view.PhotoListItem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by TECHIN1 on 6/11/2559.
 */

public class GroupListAdapter extends BaseAdapter {

    ArrayList<GroupMenuName> gm;
    Context ct;
    ViewHolder holder;

    public GroupListAdapter(ArrayList<GroupMenuName> gm, Context ct) {
        this.ct = ct;
        this.gm = gm;
        System.out.println(gm);
    }

    @Override
    public int getCount() {
        return gm.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View vi = view;
        if (view == null) {
            vi = View.inflate(ct, R.layout.namegroups, null);
            holder = new ViewHolder();

            holder.imvgroup = (ImageView) vi.findViewById(R.id.imvgroup);
            holder.tvnamegroupG = (TextView) vi.findViewById(R.id.tvnamegroupG);

            vi.setTag(holder);
        } else holder = (ViewHolder) vi.getTag();

        holder.tvnamegroupG.setText(gm.get(position).getNameGroup());

        Glide.with(parent.getContext())
                .load(gm.get(position).getIMGroup())
                .into(holder.imvgroup);

        return vi;

    }

    static class ViewHolder {

        ImageView imvgroup;
        TextView tvnamegroupG;

    }



}
