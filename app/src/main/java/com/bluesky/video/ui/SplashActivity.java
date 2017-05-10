package com.bluesky.video.ui;


import android.content.Intent;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.presenter.SplashPresenter;
import com.bluesky.video.presenter.contract.SplashContract;
import com.bluesky.video.utils.NetworkUtils;

/**
 * Created by duchao on 2017/5/5.
 */

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.registeUser();
        } else {
            //网络错误，提示弹窗
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
