package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;

/**
 * Created by duchao on 2017/5/6.
 */

public interface SplashContract {
    interface View extends BaseView {
        void jumpToMain();

    }
    interface Presenter extends BasePresenter<View> {
//        void isUserRegister();
//        void delayTodo(long millSeconds);
        void registeUser();
    }
}
