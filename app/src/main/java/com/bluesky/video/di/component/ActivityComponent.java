package com.bluesky.video.di.component;

import android.app.Activity;

import com.bluesky.video.di.module.ActivityModule;
import com.bluesky.video.di.scope.ActivityScope;
import com.bluesky.video.ui.SplashActivity;

import dagger.Component;

/**
 * Created by duchao on 2017/5/5.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
    void inject(SplashActivity splashActivity);

}
