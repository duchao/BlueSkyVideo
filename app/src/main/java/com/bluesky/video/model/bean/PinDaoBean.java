package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duchao on 2017/5/27.
 */

public class PinDaoBean {

    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("videoTypeId")
    private String mVideTypeId;
    @SerializedName("videoTypeName")
    private String mVideoTypeName;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getVideTypeId() {
        return mVideTypeId;
    }

    public void setVideTypeId(String videTypeId) {
        this.mVideTypeId = videTypeId;
    }

    public String getVideoTypeName() {
        return mVideoTypeName;
    }

    public void setVideoTypeName(String videoTypeName) {
        this.mVideoTypeName = videoTypeName;
    }
}
