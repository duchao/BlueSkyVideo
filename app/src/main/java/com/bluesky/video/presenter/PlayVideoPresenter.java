package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.PlayVideoContract;
import com.bluesky.video.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/10.
 */

public class PlayVideoPresenter extends RxPresenter<PlayVideoContract.View>
        implements PlayVideoContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private Disposable mTimeSubscrition;
    @Inject
    public PlayVideoPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void preparePlayVideo() {
        Disposable rxSubsction = Flowable.timer(2, TimeUnit.SECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.playVideo();
                    }
                });
        addSubscribe(rxSubsction);
    }

    @Override
    public void startControllbarUpdate() {
        mTimeSubscrition = Flowable.interval(1, TimeUnit.SECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.controllbarUpdate();
                    }
                });
        addSubscribe(mTimeSubscrition);
    }

    @Override
    public void stopControllbarUpdte() {
        mTimeSubscrition.isDisposed();
    }

    @Override
    public void startCountDown(long time) {
        Disposable rxSubscrition = Flowable.timer(time, TimeUnit.SECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.countDownCallback();
                    }
                });
        addSubscribe(rxSubscrition);
    }

}
