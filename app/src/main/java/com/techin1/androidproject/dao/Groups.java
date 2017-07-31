package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class Groups {

    @SerializedName("success")
    private int success;
    @SerializedName("gname")
    private List<String> Gname = new ArrayList<>();
    @SerializedName("g")
    private int sumG;
    @SerializedName("IDG")
    private List<Integer> IDG = new ArrayList<>();
    @SerializedName("IMGroup")
    private List<String> IMGroup = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<String> getGname() {
        return Gname;
    }

    public void setGname(List<String> gname) {
        Gname = gname;
    }

    public int getSumG() {
        return sumG;
    }

    public void setSumG(int sumG) {
        this.sumG = sumG;
    }

    public List<Integer> getIDG() {
        return IDG;
    }

    public void setIDG(List<Integer> IDG) {
        this.IDG = IDG;
    }

    public List<String> getIMGroup() {
        return IMGroup;
    }

    public void setIMGroup(List<String> IMGroup) {
        this.IMGroup = IMGroup;
    }
}
