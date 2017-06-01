package com.techin1.androidproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TECHIN1 on 3/3/2560.
 */

public class TeststatusModel implements Parcelable{
    protected TeststatusModel(Parcel in) {
    }

    public static final Creator<TeststatusModel> CREATOR = new Creator<TeststatusModel>() {
        @Override
        public TeststatusModel createFromParcel(Parcel in) {
            return new TeststatusModel(in);
        }

        @Override
        public TeststatusModel[] newArray(int size) {
            return new TeststatusModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
