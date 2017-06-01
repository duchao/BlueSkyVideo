package com.bluesky.video.di.component;

import android.app.Activity;

import com.bluesky.video.di.module.FragmentModule;
import com.bluesky.video.di.scope.FragmentScope;
import com.bluesky.video.ui.fragment.TabEightFragment;
import com.bluesky.video.ui.fragment.TabFiveFragment;
import com.bluesky.video.ui.fragment.TabFourFragment;
import com.bluesky.video.ui.fragment.TabNineFragment;
import com.bluesky.video.ui.fragment.TabOneFragment;
import com.bluesky.video.ui.fragment.TabSevenFragment;
import com.bluesky.video.ui.fragment.TabSixFragment;
import com.bluesky.video.ui.fragment.TabThreeFragment;
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
    void inject(TabThreeFragment tabTreeFragment);
    void inject(TabFourFragment tabFourFragment);
    void inject(TabFiveFragment tabFiveFragment);
    void inject(TabSixFragment tabSixFragment);
    void inject(TabSevenFragment tabSevenFragment);
    void inject(TabEightFragment tabEightFragment);
    void inject(TabNineFragment tabNineFragment);


}
