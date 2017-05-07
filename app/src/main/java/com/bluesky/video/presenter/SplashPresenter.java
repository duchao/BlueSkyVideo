package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.IsUserRegisterBean;
import com.bluesky.video.model.bean.RegisterData;
import com.bluesky.video.model.bean.UserBean;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.model.manager.UserInfoManager;
import com.bluesky.video.presenter.contract.SplashContract;
import com.bluesky.video.utils.PreferencesConfigs;
import com.bluesky.video.utils.RxUtil;

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
    public void isUserRegister() {
       Disposable rxSubscrition = mRetrofitHelper.getIsUserRegistInfo()
                .compose(RxUtil.<IsUserRegisterBean>rxSchedulerHelper())
                .subscribe(new Consumer<IsUserRegisterBean>() {
                    @Override
                    public void accept(@NonNull IsUserRegisterBean isUserRegisterBean) throws Exception {
                        if (isUserRegisterBean.getFlag() == 0) {
                            PreferencesConfigs.getInstance()
                                    .set(PreferencesConfigs.IS_USER_REGISTER, isUserRegisterBean.getIsRegister());
                            initRegist();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(rxSubscrition);
    }

    @Override
    public void delayTodo(long millSeconds) {
        Disposable rxSubscrition = Flowable.timer(millSeconds, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.jumpToMain();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(rxSubscrition);
    }

    private void initRegist() {
        Disposable rxSubscrition = mRetrofitHelper.initRegisterInfo()
                .compose(RxUtil.<RegisterData>rxSchedulerHelper())
                .subscribe(new Consumer<RegisterData>() {
                    @Override
                    public void accept(@NonNull RegisterData registerData) throws Exception {
                        UserBean userBean = registerData.getData();
                        UserInfoManager.getInstance().setUserData(userBean);
                        mView.jumpToMain();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(rxSubscrition);
    }
}
