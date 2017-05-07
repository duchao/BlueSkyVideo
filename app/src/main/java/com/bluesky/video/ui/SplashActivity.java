package com.bluesky.video.ui;


import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.component.ImageLoader;
import com.bluesky.video.model.manager.UserInfoManager;
import com.bluesky.video.presenter.SplashPresenter;
import com.bluesky.video.presenter.contract.SplashContract;
import com.bluesky.video.utils.NetworkUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * Created by duchao on 2017/5/5.
 */

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.img_display_bg)
    ImageView mImageView;

    private static final String DISPLAY_IMG_URL = "http://cdn.jore123.com/czav/jpg/hellogirl.jpg";

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initEventAndData() {
        ImageLoader.load(this, DISPLAY_IMG_URL, mImageView);
        if (NetworkUtil.isNetworkAvailable()) {
            String userId = UserInfoManager.getInstance().getUserId();
            if (TextUtils.isEmpty(userId)) {
                mPresenter.isUserRegister();
            } else {
                mPresenter.delayTodo(3000L);
            }
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        Glide.clear(mImageView);
        super.onDestroy();
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
