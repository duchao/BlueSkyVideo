package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchVideoTypeBean {
    @SerializedName("videoTitle")
    private String mVideoTitle;
    @SerializedName("playCount")
    private String mPlayCount;
    @SerializedName("videoSize")
    private String mVideoSize;
    @SerializedName("videoUrl")
    private String mVideoUrl;
    @SerializedName("videoLength")
    private String mVideoLength;
    @SerializedName("videoImageUrl")
    private String mVideoImageUrl;

    public String getVideoTitle() {
        return mVideoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.mVideoTitle = videoTitle;
    }

    public String getPlayCount() {
        return mPlayCount;
    }

    public void setPlayCount(String playCount) {
        this.mPlayCount = playCount;
    }

    public String getVideoSize() {
        return mVideoSize;
    }

    public void setmVideSize(String videoSize) {
        this.mVideoSize = videoSize;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
    }

    public String getVideoLength() {
        return mVideoLength;
    }

    public void setVideoLength(String videoLength) {
        this.mVideoLength = videoLength;
    }

    public String getVideoImageUrl() {
        return mVideoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.mVideoImageUrl = videoImageUrl;
    }
}
