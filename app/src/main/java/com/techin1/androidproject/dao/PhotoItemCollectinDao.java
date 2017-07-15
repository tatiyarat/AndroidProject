package com.techin1.androidproject.dao;

import android.app.backup.BackupDataInputStream;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TECHIN1 on 9/11/2559.
 */

public class PhotoItemCollectinDao {
    @SerializedName("g")
    private int sumG;
    @SerializedName("admin_group")
    private int admin_group;
    @SerializedName("data")
    private List<StatusDao> data;

    public int getSumG() {
        return sumG;
    }

    public void setSumG(int sumG) {
        this.sumG = sumG;
    }

    public int getAdmin_group() {
        return admin_group;
    }

    public void setAdmin_group(int admin_group) {
        this.admin_group = admin_group;
    }

    public List<StatusDao> getData() {
        return data;
    }

    public void setData(List<StatusDao> data) {
        this.data = data;
    }


}
