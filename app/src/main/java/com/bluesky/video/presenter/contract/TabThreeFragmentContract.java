package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.VideoBean;

import java.util.List;

/**
 * Created by duchao on 2017/5/26.
 */

public interface TabThreeFragmentContract {
    interface View extends BaseView {
        void showVideoData(List<VideoBean> mVideoList);
    }
    interface Presenter extends BasePresenter<View> {
        void getVideoData(String videoType);
    }
}
