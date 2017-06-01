package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.VideoData;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.TabThreeFragmentContract;
import com.bluesky.video.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/26.
 */

public class TabThreeFragmentPresenter extends RxPresenter<TabThreeFragmentContract.View>implements TabThreeFragmentContract.Presenter{

    private RetrofitHelper mRetrofitHelper;
    @Inject
    public TabThreeFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVideoData(String videoType) {
        Disposable rxSubscrition = mRetrofitHelper.getVideoData(videoType)
                .compose(RxUtils.<VideoData>rxSchedulerHelper())
                .subscribe(new Consumer<VideoData>() {
                    @Override
                    public void accept(@NonNull VideoData homeData) throws Exception {
                        String code = homeData.getCode();
                        if(code.equals("1")) {
                            mView.showVideoData(homeData.getVideoList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);
    }
}
