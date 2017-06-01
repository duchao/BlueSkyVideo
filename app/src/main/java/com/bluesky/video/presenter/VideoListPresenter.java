package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.VideoData;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.VideoListContract;
import com.bluesky.video.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/27.
 */

public class VideoListPresenter extends RxPresenter<VideoListContract.View> implements VideoListContract.Presenter {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public VideoListPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getVideoData(String videoType) {
        Disposable rxSubscrition = mRetrofitHelper.getVideoData(videoType)
                .compose(RxUtils.<VideoData>rxSchedulerHelper())
                .subscribe(new Consumer<VideoData>() {
                    @Override
                    public void accept(@NonNull VideoData videoData) throws Exception {
                        String code = videoData.getCode();
                        if(code.equals("1")) {
                            mView.showVideoData(videoData.getVideoList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);
    }
}
