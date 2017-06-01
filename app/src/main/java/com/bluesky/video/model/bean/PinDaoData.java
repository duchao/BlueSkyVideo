package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by duchao on 2017/5/10.
 */

public class PinDaoData {
    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private ArrayList<PinDaoBean> mPinDaoList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public ArrayList<PinDaoBean> getPinDaoList() {
        return mPinDaoList;
    }

    public void setPinDaoList(ArrayList<PinDaoBean> pinDaoList) {
        this.mPinDaoList = pinDaoList;
    }
}
