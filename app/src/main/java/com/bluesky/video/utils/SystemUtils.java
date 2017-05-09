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

//    public static interface APIS {
//        public static final String BASE_HOST = "http://api.1717goal.com.cn/app/";
//
//        public static final String REGISTER = "userreg";
//
//        public static final String HOME = "video";
//
//        public static final String XILIE = BASE_HOST + "pindao";
//
//        public static final String SERCH = BASE_HOST + "search";
//
//        public static final String UP_DATA = BASE_HOST + "version";
//
//        public static final String PAY_Bak = BASE_HOST + "paybak";
//
//        public static final String LOGIN = BASE_HOST + "services/user/login";
//
//        public static final String UP_USERINFO = BASE_HOST + "services/user/update";
//    }

    public static String getIMEI() {
        String Imei = ((TelephonyManager) mApp.getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
        return Imei;
    }

    public static String getResolution() {
        return ScreenUtils.getScreenWidth() + "*" + ScreenUtils.getScreenHeight();
    }

    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }


}
