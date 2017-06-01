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
    @SerializedName("data")
    private List<StatusDao> data;

    public int getSumG() {
        return sumG;
    }

    public void setSumG(int sumG) {
        this.sumG = sumG;
    }

    public List<StatusDao> getData() {
        return data;
    }

    public void setData(List<StatusDao> data) {
        this.data = data;
    }


}
