package com.techin1.androidproject.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.techin1.androidproject.R;
import com.techin1.androidproject.dao.MessageDao;


/**
 * Created by TECHIN1 on 25/10/2559.
 */

public class PhotoListAdapter extends PagerAdapter {

    MessageDao dao;

    private Context ctx;
    private LayoutInflater inflater;

    public PhotoListAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setDao(MessageDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 0;
        if (dao.getIMstatus() == null)
            return 0;
        return dao.getIMstatus().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        String url = dao.getIMstatus().get(position);
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_view_images, container, false);
        ImageView img = (ImageView) v.findViewById(R.id.immessagr);

        Glide.with(ctx)
                .load(url)
                .placeholder(R.drawable.loading2)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);

        container.addView(v);
        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }


}


