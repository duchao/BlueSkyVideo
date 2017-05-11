package com.bluesky.video.presenter.contract;

import android.view.SurfaceView;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;

/**
 * Created by duchao on 2017/5/10.
 */

public interface PlayVideoContract {
    interface View extends BaseView {
        void showProgressbar();
        void dismissProgressbar();
        void disableSeekbar();
        void updateSeekbarProgress(int progress);
        void updateControllTime(String time);

    }
    interface Presenter extends BasePresenter<View> {
        void playVideo(String videoUrl, SurfaceView surfaceView);
        void onDestroy();
    }
}
