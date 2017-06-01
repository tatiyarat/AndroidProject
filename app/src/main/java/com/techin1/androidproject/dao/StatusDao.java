package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TECHIN1 on 9/11/2559.
 */

public class StatusDao {

    @SerializedName("SID")
    private int sid;
    @SerializedName("status")
    private String status;
    @SerializedName("IMstatus")
    private String IMstatus;
    @SerializedName("Filestatus")
    private String Filestatus;
    @SerializedName("user")
    private String user;
    @SerializedName("gname")
    private String gname;
    @SerializedName("time")
    private String time;
    @SerializedName("IMuser")
    private String IMuser;
    @SerializedName("join")
    private String join;
    @SerializedName("timeremind")
    private  String timeremind;
    @SerializedName("dataremind")
    private  String dataremind;


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIMstatus() {
        return IMstatus;
    }

    public void setIMstatus(String IMstatus) {
        this.IMstatus = IMstatus;
    }

    public String getFilestatus() {
        return Filestatus;
    }

    public void setFilestatus(String filestatus) {
        Filestatus = filestatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIMuser() {
        return IMuser;
    }

    public void setIMuser(String IMuser) {
        this.IMuser = IMuser;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getTimeremind() {
        return timeremind;
    }

    public void setTimeremind(String timeremind) {
        this.timeremind = timeremind;
    }

    public String getDataremind() {
        return dataremind;
    }

    public void setDataremind(String dataremind) {
        this.dataremind = dataremind;
    }
}