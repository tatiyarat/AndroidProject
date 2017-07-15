package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class ResetPasswordDao {

    @SerializedName("resetpassword")
    private String resetpassword;

    public String getResetpassword() {
        return resetpassword;
    }

    public void setResetpassword(String resetpassword) {
        this.resetpassword = resetpassword;
    }
}
