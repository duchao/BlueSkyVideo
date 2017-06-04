package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchKeyBean {
    @SerializedName("maglink")
    private String mMagLink;
    @SerializedName("size")
    private String mVideoSize;
    @SerializedName("title")
    private String mVideoTitle;

    public String getMagLink() {
        return mMagLink;
    }

    public void setMagLink(String magLink) {
        this.mMagLink = magLink;
    }

    public String getVideoSize() {
        return mVideoSize;
    }

    public void setVideoSize(String videoSize) {
        this.mVideoSize = videoSize;
    }

    public String getVideoTitle() {
        return mVideoTitle;
    }

    public void setmVideoTitle(String videoTitle) {
        this.mVideoTitle = videoTitle;
    }
}
