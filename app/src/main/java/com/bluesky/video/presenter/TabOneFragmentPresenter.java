package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.HomeData;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.TabOneFragmentContract;
import com.bluesky.video.utils.RxUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/10.
 */

public class TabOneFragmentPresenter extends RxPresenter<TabOneFragmentContract.View>
        implements TabOneFragmentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    @Inject
    public TabOneFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getHomeVideoData() {
        Disposable rxSubscrition = mRetrofitHelper.getHomeVideoData()
                .compose(RxUtils.<HomeData>rxSchedulerHelper())
                .subscribe(new Consumer<HomeData>() {
                    @Override
                    public void accept(@NonNull HomeData homeData) throws Exception {
                        String code = homeData.getCode();
                        if(code.equals("1")) {
                            mView.showHomeVideoData(homeData.getVideoList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);

    }
}
