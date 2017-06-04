package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchVideoTypeData {
    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private List<SearchVideoTypeBean> mSearchVideoList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public List<SearchVideoTypeBean> getSearchVideoList() {
        return mSearchVideoList;
    }

    public void setSearchVideoList(List<SearchVideoTypeBean> searchVideoList) {
        this.mSearchVideoList = searchVideoList;
    }
}
