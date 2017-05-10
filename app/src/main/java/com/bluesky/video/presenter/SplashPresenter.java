package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.config.UserInfo;
import com.bluesky.video.model.bean.RegistBean;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.SplashContract;
import com.bluesky.video.utils.RxUtils;
import com.bluesky.video.utils.StringUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/5.
 */

public class SplashPresenter extends RxPresenter<SplashContract.View> implements SplashContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SplashPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void registeUser() {
        Disposable rxSubscrition = mRetrofitHelper.registUser()
                .compose(RxUtils.<RegistBean>rxSchedulerHelper())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(@NonNull RegistBean registBean) throws Exception {
                        UserInfo userInfoBean = UserInfo.getInstance();
                        int code = registBean.getCode();
                        if (code != 1) {
                            return;
                        }
                        int level = registBean.getLevel();
                        int currentLevel = userInfoBean.getUserType();
                        String userId = registBean.getUserId();
                        String sign = registBean.getSign();
                        userInfoBean.setUserId(userId);
                        String verifySign = StringUtils.getMD5(userId + "?U$@t^OQ.%k>A=n{&3P," + level);
                        if (verifySign.equals(sign) && (level > currentLevel)) {
                            userInfoBean.setUserType(level);
                        }
                        jumpToMain(0);
                    }
                });
        addSubscribe(rxSubscrition);
    }

    private void jumpToMain(long seconds) {
        Disposable rxSubscrition = Flowable.timer(seconds, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.jumpToMain();
                    }
                });
        addSubscribe(rxSubscrition);
    }

}
