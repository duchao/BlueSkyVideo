package com.bluesky.video.model.config;

/**
 * Created by duchao on 2017/5/9.
 */

public class UserInfo {

    private static class SingletonHolder {
        private static final UserInfo INSTANCE = new UserInfo();
    }
    public static UserInfo getInstance() {
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
