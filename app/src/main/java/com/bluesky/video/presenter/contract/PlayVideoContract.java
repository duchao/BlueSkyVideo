package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;

/**
 * Created by duchao on 2017/5/10.
 */

public interface PlayVideoContract {
    interface View extends BaseView {
        void playVideo();
        void controllbarUpdate();
        void countDownCallback();
    }
    interface Presenter extends BasePresenter<View> {
        void preparePlayVideo();
        void startControllbarUpdate();
        void stopControllbarUpdte();
        void startCountDown(long time);
    }
}
