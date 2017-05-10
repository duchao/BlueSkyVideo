package com.bluesky.video.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bluesky.video.app.App;
import com.bluesky.video.di.component.DaggerFragmentComponet;
import com.bluesky.video.di.component.FragmentComponet;
import com.bluesky.video.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by duchao on 2017/5/10.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T mPresenter;
    protected abstract void initInject();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected FragmentComponet getFragmentComponent() {
        return DaggerFragmentComponet.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }
}
