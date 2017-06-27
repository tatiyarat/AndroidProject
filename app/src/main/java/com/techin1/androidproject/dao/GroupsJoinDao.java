package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class GroupsJoinDao {

    @SerializedName("count")
    private int count;
    @SerializedName("id")
    private int id;
    @SerializedName("gname")
    private String gname;
    @SerializedName("design")
    private String design;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }


}
