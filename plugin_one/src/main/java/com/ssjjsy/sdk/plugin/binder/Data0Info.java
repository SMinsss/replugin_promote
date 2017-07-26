package com.ssjjsy.sdk.plugin.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/25.
 */

public class Data0Info implements Parcelable {

    private String date = "";
    private String location = "";
    private String behavior = "";

    public Data0Info() {
    }

    protected Data0Info(Parcel in) {
        date = in.readString();
        location = in.readString();
        behavior = in.readString();
    }

    public static final Creator<Data0Info> CREATOR = new Creator<Data0Info>() {
        @Override
        public Data0Info createFromParcel(Parcel in) {
            return new Data0Info(in);
        }

        @Override
        public Data0Info[] newArray(int size) {
            return new Data0Info[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(location);
        dest.writeString(behavior);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
