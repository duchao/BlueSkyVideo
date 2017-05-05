package com.bluesky.video.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bluesky.video.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by duchao on 2017/5/5.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;
    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provicdeActivity() {
        return mFragment.getActivity();
    }
}
