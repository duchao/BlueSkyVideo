package com.bluesky.video.model.manager;

import com.bluesky.video.model.bean.UserBean;
import com.bluesky.video.utils.PreferencesConfigs;

/**
 * Created by duchao on 2017/5/7.
 */

public class UserInfoManager {

    private PreferencesConfigs mConfigs;

    private String mUserId;

    private String mPassword;

    private int mLevel;

    private static class SingletonHolder {
        private static final UserInfoManager INSTANCE = new UserInfoManager();
    }
    public static UserInfoManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private UserInfoManager() {
        mConfigs = PreferencesConfigs.getInstance();
    }

    public void setUserData(UserBean userBean) {
        if (userBean != null) {
            mUserId = userBean.getUserid();
            mPassword = userBean.getPassword();
            mLevel = userBean.getLeve();
            mConfigs.set(PreferencesConfigs.LOCAL_SHARE_USER_ID, mUserId);
            mConfigs.set(PreferencesConfigs.LOCAL_SHARE_PASSWORD, mPassword);
            mConfigs.set(PreferencesConfigs.LOCAL_SHARE_LEVEL, mLevel);
            mConfigs.set(PreferencesConfigs.LOCAL_SHARE_APP_CONFIG, userBean.getAppconfig().toString());
        }

    }

    public String getUserId() {
        return mConfigs.getString(PreferencesConfigs.LOCAL_SHARE_USER_ID, "");
    }

}
