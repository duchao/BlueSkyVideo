package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.PinDaoData;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.TabNineFragmentContract;
import com.bluesky.video.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/6/1.
 */

public class TabNineFragmentPresenter extends RxPresenter<TabNineFragmentContract.View> implements TabNineFragmentContract.Preseneter {

    RetrofitHelper mRetrofitHelper;

    @Inject
    public TabNineFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getPinDaoData(String parentId) {
        Disposable rxSubscrition = mRetrofitHelper.getPinDaoData(parentId)
                .compose(RxUtils.<PinDaoData>rxSchedulerHelper())
                .subscribe(new Consumer<PinDaoData>() {
                    @Override
                    public void accept(@NonNull PinDaoData pinDaoData) throws Exception {
                        String code = pinDaoData.getCode();
                        if (code.equals("1")) {
                            mView.showPinDaoData(pinDaoData.getPinDaoList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);
    }
}
