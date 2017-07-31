package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by techin on 23/7/2560.
 */

public class ForgotPasswordDao {

    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
