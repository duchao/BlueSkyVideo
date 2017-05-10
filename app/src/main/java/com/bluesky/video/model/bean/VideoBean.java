package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duchao on 2017/5/10.
 */

public class VideoBean {
    @SerializedName("created")
    private String mCreated;
    @SerializedName("createdby")
    private String mCreatedBy;
    @SerializedName("isActive")
    private String mIsActived;
    private String mPlayCount;
    private String mQx = "4";
    @SerializedName("recommend")
    private String mRecommend;
    private String mRecommendLevel;
    @SerializedName("remarks")
    private String mRemarks;
    @SerializedName("videoBigImageUrl")
    private String mVideoBigImageUrl;
    @SerializedName("videoContens")
    private String mVideoContent;
    @SerializedName("videoId")
    private String mVideoId;
    @SerializedName("videoImageUrl")
    private String mVideoImageUrl;
    @SerializedName("videoLength")
    private String mVideoLength;
    @SerializedName("videoModule")
    private String mVideoModule;
    @SerializedName("videoTitle")
    private String mVideoTitle;
    @SerializedName("videoTryUrl")
    private String mVideoTryUrl;
    @SerializedName("videoType")
    private String mVideoType;
    @SerializedName("videoUrl")
    private String mVideoUrl;

    public String getCreated() {
        return mCreated;
    }

    public void setCrated(String created) {
        mCreated = created;
    }

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public String getIsActived() {
        return mIsActived;
    }

    public void setIsActived(String isActived) {
        mIsActived = isActived;
    }

    public String getPlayCount() {
        return mPlayCount;
    }

    public void setPlayCount(String playCount) {
        mPlayCount = playCount;
    }

    public String getQx() {
        return mQx;
    }

    public void setQx(String qx) {
        mQx = qx;
    }

    public String getRecommend() {
        return mRecommend;
    }

    public void setRecommend(String recommend) {
        mRecommend = recommend;
    }

    public String getRecommendLevel() {
        return mRecommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        mRecommendLevel = recommendLevel;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    public String getVideoBigImageUrl() {
        return mVideoBigImageUrl;
    }

    public void setVideoBigImageUrl(String videoBigImageUrl) {
        mVideoBigImageUrl = videoBigImageUrl;
    }

    public String getVideoContent() {
        return mVideoContent;
    }

    public void setVideoContent(String videoContent) {
        mVideoContent = videoContent;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public void setVideoId(String videoId) {
        mVideoId = videoId;
    }

    public String getVideoImageUrl() {
        return mVideoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        mVideoImageUrl = videoImageUrl;
    }

    public String getVideoLength() {
        return mVideoLength;
    }

    public void setVideoLength(String videoLength) {
        mVideoLength = videoLength;
    }

    public String getVideoModule() {
        return mVideoModule;
    }

    public void setVideoModule(String videoModule) {
        mVideoModule = videoModule;
    }

    public String getVideoTitle() {
        return mVideoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        mVideoTitle = videoTitle;
    }

    public String getVideoTryUrl() {
        return mVideoTryUrl;
    }

    public void setVideoTryUrl(String videoTryUrl) {
        mVideoTryUrl = videoTryUrl;
    }

    public String getVideoType() {
        return mVideoType;
    }

    public void setVideoType(String videoType) {
        mVideoType = videoType;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }
}
