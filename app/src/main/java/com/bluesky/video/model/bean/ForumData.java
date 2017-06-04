package com.bluesky.video.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class ForumData {

    @SerializedName("code")
    private String mCode;
    @SerializedName("data")
    private List<ForumBean> mForumList;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public List<ForumBean> getForumList() {
        return mForumList;
    }

    public void setForumList(List<ForumBean> forumList) {
        this.mForumList = forumList;
    }
}
