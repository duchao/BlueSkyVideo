package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.PlayVideoContract;

import javax.inject.Inject;

/**
 * Created by duchao on 2017/5/10.
 */

public class PlayVideoPresenter extends RxPresenter<PlayVideoContract.View>
        implements PlayVideoContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public PlayVideoPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }
}
