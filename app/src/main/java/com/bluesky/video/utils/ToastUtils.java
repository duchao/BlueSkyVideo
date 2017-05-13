package com.bluesky.video.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by duchao on 2017/5/10.
 */

public class ToastUtils {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }


    public static void showToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_SHORT).show();
    }


    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static void showLongToast(Context context, int resId) {
        Toast.makeText(context, context.getResources().getText(resId),
                Toast.LENGTH_LONG).show();
    }
}
