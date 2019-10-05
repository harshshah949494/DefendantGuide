package com.example.defendantguide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @Expose
    @SerializedName("app")
    String app1;

    @Expose
    @SerializedName("user")
    String user1;

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getApp1() {
        return app1;
    }

    public void setApp1(String app1) {
        this.app1 = app1;
    }
}
