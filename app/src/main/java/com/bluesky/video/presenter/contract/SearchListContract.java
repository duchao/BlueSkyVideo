package com.bluesky.video.presenter.contract;

import com.bluesky.video.base.BasePresenter;
import com.bluesky.video.base.BaseView;
import com.bluesky.video.model.bean.SearchKeyBean;
import com.bluesky.video.model.bean.SearchVideoTypeBean;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public interface SearchListContract {
    interface View extends BaseView {
        void showSearchDataForKey(List<SearchKeyBean> searchKeyList);
        void showSearchDataForVideoType(List<SearchVideoTypeBean> searchVideoTypeList);
    }
    interface Presenter extends BasePresenter<View> {
        void getSearchDataByKey(String key);
        void getSearchDataByVideoType(String videoType);
    }
}
