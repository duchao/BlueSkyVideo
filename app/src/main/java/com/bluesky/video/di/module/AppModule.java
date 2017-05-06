package com.bluesky.video.di.module;

import com.bluesky.video.app.App;
import com.bluesky.video.model.http.RetrofitHelper;

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

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper() {
        return  new RetrofitHelper();
    }

}
