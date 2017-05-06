package com.bluesky.video.model.bean;

/**
 * Created by duchao on 2017/5/6.
 */

public class IsUserRegisterBean {
    private int flag = -1;
    private String msg;
    private String isRegister;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }
}
