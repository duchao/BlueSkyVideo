package com.bluesky.video.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.bluesky.video.component.InitializeService;
import com.bluesky.video.di.component.AppComponent;
import com.bluesky.video.di.component.DaggerAppComponent;
import com.bluesky.video.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by duchao on 2017/5/5.
 */

public class App extends Application {
    private Set<Activity> mActivities;
    private static App mInstance;
    private static AppComponent mAppComponent;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        getScreenSize();
        InitializeService.start(this);

    }

    public static App getInstance() {
        return mInstance;
    }

    public void addActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new HashSet<Activity>();
        }
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (mActivities != null) {
            mActivities.remove(activity);
        }
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .build();
        }
        return  mAppComponent;

    }

    public void exitApp() {
        if (mActivities != null) {
            synchronized (mActivities) {
                for (Activity activity : mActivities) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
