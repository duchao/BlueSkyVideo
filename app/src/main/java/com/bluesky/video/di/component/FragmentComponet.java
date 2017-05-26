package com.bluesky.video.di.component;

import android.app.Activity;

import com.bluesky.video.di.module.FragmentModule;
import com.bluesky.video.di.scope.FragmentScope;
import com.bluesky.video.ui.fragment.TabOneFragment;
import com.bluesky.video.ui.fragment.TabTwoFragment;

import dagger.Component;

/**
 * Created by duchao on 2017/5/5.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponet {
    Activity getActivity();
    void inject(TabOneFragment tabOneFragment);
    void inject(TabTwoFragment tabTwoFragment);
}
