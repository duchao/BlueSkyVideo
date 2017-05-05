package com.bluesky.video.di.module;

import com.bluesky.video.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duchao on 2017/5/5.
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return mApp;
    }

}
