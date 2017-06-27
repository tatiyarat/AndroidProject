package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class LogoutDao {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
