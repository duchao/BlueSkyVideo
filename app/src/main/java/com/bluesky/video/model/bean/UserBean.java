package com.bluesky.video.model.bean;

/**
 * Created by duchao on 2017/5/6.
 */
public class UserBean {
    private AppConfigBean appconfig;
    private int leve;
    private String password;
    private String userid;

    public AppConfigBean getAppconfig() {
        return appconfig;
    }

    public void setAppconfig(AppConfigBean appconfig) {
        this.appconfig = appconfig;
    }

    public int getLeve() {
        return leve;
    }

    public void setLeve(int leve) {
        this.leve = leve;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
