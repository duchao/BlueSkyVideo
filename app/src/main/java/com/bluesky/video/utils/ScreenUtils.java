package com.bluesky.video.utils;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.bluesky.video.app.App;

/**
 * Created by duchao on 2017/5/7.
 */

public class ScreenUtils {

    private static Application mApp = App.getInstance();

    public static int dip2px(Context paramContext, float paramFloat) {
        return (int) (paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int px2dip(Context paramContext, float paramFloat) {
        return (int) (paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) mApp.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return 640;
        } else {
            return wm.getDefaultDisplay().getWidth();
        }
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) mApp
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return 1240;
        } else {
            return wm.getDefaultDisplay().getHeight();
        }
    }

}
