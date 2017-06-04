package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duchao on 2017/6/2.
 */

public class ForumBean {

    @SerializedName("sendNum")
    private String mBbsSendNum;
    @SerializedName("returnNum")
    private String mBbsReturnNum;
    @SerializedName("videoContens")
    private String mContent;
    @SerializedName("videoImageUrl")
    private String mImageURL;
    @SerializedName("videoTitle")
    private String mTitle;

    public String getBbbSendNum() {
        return mBbsSendNum;
    }

    public void setBbsSendNum(String bbsSendNum) {
        this.mBbsSendNum = bbsSendNum;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String imageURL) {
        this.mImageURL = imageURL;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getBbsReturnNum() {
        return mBbsReturnNum;
    }

    public void setBbsReturnNum(String bbsReturnNum) {
        this.mBbsReturnNum = bbsReturnNum;
    }
}
