package com.bluesky.video.di.component;

import com.bluesky.video.app.App;
import com.bluesky.video.di.module.AppModule;
import com.bluesky.video.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by duchao on 2017/5/5.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App getContext();
    RetrofitHelper getRetrofitHelper();
}
