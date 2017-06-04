package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.SearchKeyData;
import com.bluesky.video.model.bean.SearchVideoTypeData;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.SearchListContract;
import com.bluesky.video.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchListPresenter extends RxPresenter<SearchListContract.View> implements SearchListContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    @Inject
    public SearchListPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getSearchDataByKey(String key) {
        Disposable rxSubsrition = mRetrofitHelper.getSearchKeyData(key)
                .compose(RxUtils.<SearchKeyData>rxSchedulerHelper())
                .subscribe(new Consumer<SearchKeyData>() {
                    @Override
                    public void accept(@NonNull SearchKeyData searchKeyData) throws Exception {
                        String code = searchKeyData.getCode();
                        if (code.equals("1")) {
                            mView.showSearchDataForKey(searchKeyData.getSearchKeyList());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        // nothing to do
                    }
                });
        addSubscribe(rxSubsrition);
    }

    @Override
    public void getSearchDataByVideoType(String videoType) {
        Disposable rxSubscrition = mRetrofitHelper.getSearchVideoTypeData(videoType)
                .compose(RxUtils.<SearchVideoTypeData>rxSchedulerHelper())
                .subscribe(new Consumer<SearchVideoTypeData>() {
                    @Override
                    public void accept(@NonNull SearchVideoTypeData searchVideoTypeData) throws Exception {
                        String code = searchVideoTypeData.getCode();
                        if (code.equals("1")) {
                            mView.showSearchDataForVideoType(searchVideoTypeData.getSearchVideoList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);
    }
}
