package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by TECHIN1 on 14/11/2559.
 */

public class MessageDao {

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
    private Date time;
    @SerializedName("IMuser")
    private String IMuser;
    @SerializedName("join")
    private String join;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}
