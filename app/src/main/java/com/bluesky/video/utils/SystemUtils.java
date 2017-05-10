package com.bluesky.video.utils;

import android.app.Application;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.bluesky.video.app.App;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by duchao on 2017/5/9.
 * 系统相关的信息
 */

public class SystemUtils {

    private static Application mApp = App.getInstance();

    public static String getIMEI() {
        String imei = ((TelephonyManager) mApp.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    public static String getResolution() {
        return ScreenUtils.getScreenWidth() + "*" + ScreenUtils.getScreenHeight();
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }


}
