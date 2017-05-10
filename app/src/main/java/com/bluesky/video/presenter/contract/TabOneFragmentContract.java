package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.VideoBean;

import java.util.ArrayList;

/**
 * Created by duchao on 2017/5/10.
 */

public interface TabOneFragmentContract {
    interface View extends BaseView {
        void showHomeVideoData(ArrayList<VideoBean> mVideoList);
    }
    interface Presenter extends BasePresenter<View> {
        void getHomeVideoData();
    }
}
