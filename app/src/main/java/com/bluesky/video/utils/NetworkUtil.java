package com.bluesky.video.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.bluesky.video.app.App;

/**
 * Created by duchao on 2017/5/6.
 */

public class NetworkUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
