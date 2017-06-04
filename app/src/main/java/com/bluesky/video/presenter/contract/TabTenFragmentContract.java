package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.ForumBean;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public interface TabTenFragmentContract {
    interface View extends BaseView {
        void showForumData(List<ForumBean> forumList);
    }

    interface Presenter extends BasePresenter<View> {
        void getForumData(String videoType);

    }
}
