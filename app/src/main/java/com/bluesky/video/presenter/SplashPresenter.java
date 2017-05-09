package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.config.UserInfoBean;
import com.bluesky.video.model.bean.RegistBean;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.SplashContract;
import com.bluesky.video.utils.RxUtils;
import com.bluesky.video.utils.StringUtils;

import javax.inject.Inject;

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
                        RegistBean registBean1 = registBean;
                        UserInfoBean userInfoBean = UserInfoBean.getInstance();
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
                        mView.jumpToMain();
                    }
                });
        addSubscribe(rxSubscrition);
    }

//    @Override
//    public void isUserRegister() {
//       Disposable rxSubscrition = mRetrofitHelper.getIsUserRegistInfo()
//                .compose(RxUtils.<IsUserRegisterBean>rxSchedulerHelper())
//                .subscribe(new Consumer<IsUserRegisterBean>() {
//                    @Override
//                    public void accept(@NonNull IsUserRegisterBean isUserRegisterBean) throws Exception {
//                        if (isUserRegisterBean.getFlag() == 0) {
//                            SharePreferencesHelper.getInstance()
//                                    .set(SharePreferencesHelper.IS_USER_REGISTER, isUserRegisterBean.getIsRegister());
//                            initRegist();
//                        }
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//
//                    }
//                });
//        addSubscribe(rxSubscrition);
//    }

//    @Override
//    public void delayTodo(long millSeconds) {
//
//        Disposable rxSubscrition = Flowable.timer(millSeconds, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        mView.jumpToMain();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//
//                    }
//                });
//
//        addSubscribe(rxSubscrition);
//    }



//    private void initRegist() {
//        Disposable rxSubscrition = mRetrofitHelper.initRegisterInfo()
//                .compose(RxUtils.<RegisterData>rxSchedulerHelper())
//                .subscribe(new Consumer<RegisterData>() {
//                    @Override
//                    public void accept(@NonNull RegisterData registerData) throws Exception {
//                        UserBean userBean = registerData.getData();
//                        UserInfo1Manager.getInstance().setUserData(userBean);
//                        mView.jumpToMain();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//
//                    }
//                });
//        addSubscribe(rxSubscrition);
//    }
}
