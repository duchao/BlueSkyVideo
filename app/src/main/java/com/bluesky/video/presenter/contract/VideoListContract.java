package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.VideoBean;

import java.util.List;

/**
 * Created by duchao on 2017/5/27.
 */

public interface VideoListContract {
    interface View extends BaseView {
        void showVideoData(List<VideoBean> videList);
    }
    interface Presenter extends BasePresenter<View> {
        void getVideoData(String videoType);
    }
}
