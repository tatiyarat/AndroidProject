package com.techin1.androidproject.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TECHIN1 on 24/10/2559.
 */

public class Login {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String Name;
    @SerializedName("iduser")
    private String iduser;
    @SerializedName("pass")
    private String pass;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("number")
    private String number;
    @SerializedName("mail")
    private String mail;
    @SerializedName("IM")
    private String im;
    @SerializedName("success")
    private int success;



}
