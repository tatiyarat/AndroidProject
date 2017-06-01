package com.techin1.androidproject;

/**
 * Created by TECHIN1 on 7/11/2559.
 */

public class GroupMenuName {
    String NameGroup;
    String IMGroup;
    int IDG;

    public GroupMenuName(String NameGroup, String IMGroup,int IDG){

        this.NameGroup = NameGroup;
        this.IMGroup = IMGroup;
        this.IDG = IDG;
    }

    public String getIMGroup() {
        return IMGroup;
    }

    public String getNameGroup() {
        return NameGroup;
    }

    public void setIMGroup(String IMGroup) {
        this.IMGroup = IMGroup;
    }

    public void setNameGroup(String nameGroup) {
        NameGroup = nameGroup;
    }

    public int getIDG() {
        return IDG;
    }

    public void setIDG(int IDG) {
        this.IDG = IDG;
    }
}
