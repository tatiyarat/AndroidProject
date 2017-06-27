package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TECHIN1 on 9/11/2559.
 */

public class GroupJoinDataDao {

    @SerializedName("data")
    private List<GroupsJoinDao> data;

    public List<GroupsJoinDao> getData() {
        return data;
    }

    public void setData(List<GroupsJoinDao> data) {
        this.data = data;
    }
}
