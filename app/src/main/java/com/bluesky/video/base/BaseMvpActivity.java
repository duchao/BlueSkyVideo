package com.bluesky.video.base;

import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

/**
 * Created by duchao on 2017/5/5.
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
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

}
