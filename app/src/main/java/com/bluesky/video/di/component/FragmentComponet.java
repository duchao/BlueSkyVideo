package com.bluesky.video.di.component;

import android.app.Activity;

import com.bluesky.video.di.module.FragmentModule;
import com.bluesky.video.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by duchao on 2017/5/5.
 */
@FragmentScope
@Component(dependencies = AppComponet.class, modules = FragmentModule.class)
public interface FragmentComponet {
    Activity getActivity();
}
