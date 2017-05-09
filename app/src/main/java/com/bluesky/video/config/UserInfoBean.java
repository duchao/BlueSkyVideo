package com.bluesky.video.config;

/**
 * Created by duchao on 2017/5/9.
 */

public class UserInfoBean {

    private static class SingletonHolder {
        private static final UserInfoBean INSTANCE = new UserInfoBean();
    }
    public static UserInfoBean getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private String mUserId;
    private int mUserType = 1;

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public int getUserType() {
        return mUserType;
    }

    public void setUserType(int userType) {
        mUserType = userType;
    }
}
