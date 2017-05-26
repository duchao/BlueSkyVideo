package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by duchao on 2017/5/10.
 */

public class VideoData {
    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private ArrayList<VideoBean> mVideoList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public ArrayList<VideoBean> getVideoList() {
        return mVideoList;
    }

    public void setVideoList(ArrayList<VideoBean> videoList) {
        this.mVideoList = videoList;
    }
}
