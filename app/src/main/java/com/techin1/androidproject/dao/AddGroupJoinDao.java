package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class AddGroupJoinDao {

    @SerializedName("show")
    private String show;

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
