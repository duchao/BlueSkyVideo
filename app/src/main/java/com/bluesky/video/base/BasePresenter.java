package com.bluesky.video.base;

/**
 * Created by duchao on 2017/5/5.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
