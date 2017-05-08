package com.bluesky.video.utils;

import android.content.Context;

/**
 * Created by duchao on 2017/5/7.
 */

public class ScreenUtils {

    public static int dip2px(Context paramContext, float paramFloat)
    {
        return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int px2dip(Context paramContext, float paramFloat)
    {
        return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
    }

}
