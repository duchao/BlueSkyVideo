package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.PinDaoBean;

import java.util.List;

/**
 * Created by duchao on 2017/5/26.
 */

public interface TabEightFragmentContract {
    interface View extends BaseView {
        void showPinDaoData(List<PinDaoBean> mPinDaoList);
    }
    interface Presenter extends BasePresenter<View> {
        void getPinDaoData(String parentId);

    }
}
