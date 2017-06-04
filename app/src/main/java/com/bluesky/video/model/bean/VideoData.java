package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duchao on 2017/5/10.
 */

public class VideoData {
    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private List<VideoBean> mVideoList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public List<VideoBean> getVideoList() {
        return mVideoList;
    }

    public void setVideoList(List<VideoBean> videoList) {
        this.mVideoList = videoList;
    }
}
