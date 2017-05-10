package com.bluesky.video.app;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

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

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
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

}
