package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class GetDateCalendarDao {

    @SerializedName("id_s")
    private int id_s;
    @SerializedName("year")
    private int year;
    @SerializedName("month")
    private int month;
    @SerializedName("day")
    private int day;
    @SerializedName("hh")
    private int hh;
    @SerializedName("mm")
    private int mm;
    @SerializedName("ss")
    private int ss;

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHh() {
        return hh;
    }

    public void setHh(int hh) {
        this.hh = hh;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getSs() {
        return ss;
    }

    public void setSs(int ss) {
        this.ss = ss;
    }
}
