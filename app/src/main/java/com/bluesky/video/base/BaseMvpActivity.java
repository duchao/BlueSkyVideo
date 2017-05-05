package com.bluesky.video.base;

import android.support.v7.app.AppCompatDelegate;

import com.bluesky.video.app.App;
import com.bluesky.video.di.component.ActivityComponent;
import com.bluesky.video.di.component.DaggerActivityComponent;
import com.bluesky.video.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by duchao on 2017/5/5.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;
    protected abstract void initInject();

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showErrorMsg(String msg) {
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }


}
