package com.bluesky.video.di.component;

import android.app.Activity;

import com.bluesky.video.di.module.ActivityModule;
import com.bluesky.video.di.scope.ActivityScope;
import com.bluesky.video.ui.activity.MainActivity;
import com.bluesky.video.ui.activity.PlayVideoActivity;
import com.bluesky.video.ui.activity.SplashActivity;

import dagger.Component;

/**
 * Created by duchao on 2017/5/5.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(SplashActivity splashActivity);
    void inject(MainActivity mainActivity);
    void inject(PlayVideoActivity playVideoActivity);

}
