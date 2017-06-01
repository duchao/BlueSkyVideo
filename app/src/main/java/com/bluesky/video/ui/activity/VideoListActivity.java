package com.bluesky.video.ui.activity;


import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.presenter.VideoListPresenter;
import com.bluesky.video.presenter.contract.VideoListContract;
import com.bluesky.video.ui.adapter.VideoListGridAdapter;
import com.bluesky.video.utils.NetworkUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by duchao on 2017/5/27.
 */

public class VideoListActivity extends BaseMvpActivity<VideoListPresenter> implements VideoListContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.gv_activity_video_list_content)
    GridView mGridView;
    VideoListGridAdapter mVideoListGridAdapter;
    @Override
    public void showVideoData(ArrayList<VideoBean> videoList) {
        mVideoListGridAdapter = new VideoListGridAdapter(mContext, videoList);
        mGridView.setAdapter(mVideoListGridAdapter);
        mVideoListGridAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initEventAndData() {
        String videoType = getIntent().getStringExtra("id");
        String titleName = getIntent().getStringExtra("name");
        setToolbar(mToolbar, titleName);
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getVideoData(videoType);
        }

    }
}
