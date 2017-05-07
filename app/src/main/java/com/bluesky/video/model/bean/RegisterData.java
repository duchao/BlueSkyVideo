package com.bluesky.video.model.bean;

/**
 * Created by duchao on 2017/5/6.
 */

public class RegisterData {
    private int flag;
    private UserBean data;
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }
}
