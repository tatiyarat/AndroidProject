package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TECHIN1 on 14/11/2559.
 */

public class MessageDao {

    @SerializedName("format")
    private String format;
    @SerializedName("SID")
    private int sid;
    @SerializedName("result")
    private int result;
    @SerializedName("status")
    private String status;
    @SerializedName("IMstatus")
    private List<String> IMstatus = new ArrayList<>();
    @SerializedName("Filestatus")
    private String Filestatus;
    @SerializedName("filename")
    private String filename;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getIMstatus() {
        return IMstatus;
    }

    public void setIMstatus(List<String> IMstatus) {
        this.IMstatus = IMstatus;
    }

    public String getFilestatus() {
        return Filestatus;
    }

    public void setFilestatus(String filestatus) {
        Filestatus = filestatus;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
}
