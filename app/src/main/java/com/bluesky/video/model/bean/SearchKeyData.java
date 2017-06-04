package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchKeyData {
    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private List<SearchKeyBean> mSearchKeyList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public List<SearchKeyBean> getSearchKeyList() {
        return mSearchKeyList;
    }

    public void setSearchKeyList(List<SearchKeyBean> searchKeyList) {
        this.mSearchKeyList = searchKeyList;
    }
}
