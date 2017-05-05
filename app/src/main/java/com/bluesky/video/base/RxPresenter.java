package com.bluesky.video.base;

/**
 * Created by duchao on 2017/5/5.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    @Override
    public void attachView(T view) {

    }

    @Override
    public void detachView() {

    }
}
