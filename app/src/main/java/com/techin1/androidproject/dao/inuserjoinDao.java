package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TECHIN1 on 18/11/2559.
 */

public class inuserjoinDao {
    @SerializedName("join")
    private String join;
    @SerializedName("success")
    private int success;

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
