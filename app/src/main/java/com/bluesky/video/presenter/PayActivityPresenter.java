package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.PayActivityContract;

import javax.inject.Inject;

/**
 * Created by duchao on 2017/6/4.
 */

public class PayActivityPresenter extends RxPresenter<PayActivityContract.View> implements PayActivityContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public PayActivityPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }
}
