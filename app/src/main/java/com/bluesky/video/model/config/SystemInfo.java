package com.bluesky.video.model.config;

import com.bluesky.video.utils.AppUtils;
import com.bluesky.video.utils.StringUtils;
import com.bluesky.video.utils.SystemUtils;


/**
 * Created by duchao on 2017/5/9.
 */

public class SystemInfo {

    private static class SingletonHolder {
        private static final SystemInfo INSTANCE = new SystemInfo();
    }

    public static SystemInfo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private String mChannel;

    private String mVersionName;

    private int mVersionCode;

    private String mIMEI;

    private String mResolution;

    private String mAppName;

    private String mPackageName;

    private String mTime;

    private String mSign;

    private String mOSVersion;

    public SystemInfo() {
        mChannel = AppUtils.getChannel();
        mVersionName = AppUtils.getVersionName();
        mVersionCode = AppUtils.getVersionCode();
        mIMEI = SystemUtils.getIMEI();
        mResolution = SystemUtils.getResolution();
        mAppName = AppUtils.getAppName();
        mPackageName = AppUtils.getPackageName();
        mTime = initTime();
        mSign = initSign(mChannel, mIMEI, mTime);
        mOSVersion = SystemUtils.getOSVersion();
    }

    private String initTime() {
        long time = System.currentTimeMillis() / 1000L;
        return String.valueOf(time);
    }

    private String initSign(String channel, String deviceId, String time) {
        String key = channel + deviceId + time + "?U$@t^OQ.%k>A=n{&3P,";
        return StringUtils.getMD5(key);
    }

    public String getChannel() {
        return mChannel;
    }

    public void setChannel(String channel) {
        mChannel = channel;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public void setVersionName(String versionName) {
        mVersionName = versionName;
    }

    public int getVersionCode() {
        return mVersionCode;
    }

    public void setVersionCode(int versionCode) {
        mVersionCode = versionCode;
    }

    public String getIMEI() {
        return mIMEI;
    }

    public void setIMEI(String IMEI) {
        mIMEI = IMEI;
    }

    public String getResolution() {
        return mResolution;
    }

    public void setResolution(String resolution) {
        mResolution = resolution;
    }

    public String getAppName() {
        return mAppName;
    }

    public void setAppName(String appName) {
        mAppName = appName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getSign() {
        return mSign;
    }

    public void setSign(String sign) {
        mSign = sign;
    }

    public String getOSVersion() {
        return mOSVersion;
    }

    public void setOSVersion(String OSVersion) {
        mOSVersion = OSVersion;
    }
}
