package com.bluesky.video.presenter;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.bean.ForumData;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.TabTenFragmentContract;
import com.bluesky.video.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/6/2.
 */

public class TabTenFragmentPresenter extends RxPresenter<TabTenFragmentContract.View> implements TabTenFragmentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    @Inject
    public TabTenFragmentPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getForumData(String videoType) {
        Disposable rxSubscrition = mRetrofitHelper.getForumData(videoType)
                .compose(RxUtils.<ForumData>rxSchedulerHelper())
                .subscribe(new Consumer<ForumData>() {
                    @Override
                    public void accept(@NonNull ForumData forumData) throws Exception {
                        String code = forumData.getCode();
                        if (code.equals("1")) {
                            mView.showForumData(forumData.getForumList());
                        }
                    }
                });
        addSubscribe(rxSubscrition);
    }
}
