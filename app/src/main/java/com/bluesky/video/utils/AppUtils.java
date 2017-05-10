package com.bluesky.video.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.bluesky.video.app.App;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by duchao on 2017/5/9.
 * app 相关的信息
 */

public class AppUtils {

    private static Application mApp = App.getInstance();

    public static String getChannel() {
        String channel ="";
        ApplicationInfo appinfo = App.getInstance().getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            if(entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String entryName = entry.getName();
                if (entryName.contains("META-INF/channel_")) {
                    channel = entryName.replace("META-INF/channel_", "");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(zipfile != null) {
                try {
                    zipfile.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return channel;
    }

    public static String getVersionName(){
        PackageManager packageManager = mApp.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(mApp.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getVersionCode() {
        PackageManager packageManager = mApp.getPackageManager();
        PackageInfo packageInfo;
        int versionCode = -1;
        try {
            packageInfo = packageManager.getPackageInfo(mApp.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getAppName() {
        PackageManager packageManager = mApp.getPackageManager();
        String appName = "";
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mApp.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            appName = mApp.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appName;
    }

    public static String getPackageName() {
        PackageManager packageManager = mApp.getPackageManager();
        PackageInfo packageInfo;
        String packageName = "";
        try {
            packageInfo = packageManager.getPackageInfo(mApp.getPackageName(), 0);
            packageName = packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

}
