package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class GetDateDao {

    @SerializedName("success")
    private int success;
    @SerializedName("data")
    private List<GetDateCalendarDao> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<GetDateCalendarDao> getData() {
        return data;
    }

    public void setData(List<GetDateCalendarDao> data) {
        this.data = data;
    }
}
