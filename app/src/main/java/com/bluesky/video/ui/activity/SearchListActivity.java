package com.bluesky.video.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.GridView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.model.bean.SearchKeyBean;
import com.bluesky.video.model.bean.SearchVideoTypeBean;
import com.bluesky.video.presenter.SearchListPresenter;
import com.bluesky.video.presenter.contract.SearchListContract;
import com.bluesky.video.ui.adapter.SearchKeyGridAdapter;
import com.bluesky.video.ui.adapter.SearchVideoTypeAdapter;
import com.bluesky.video.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchListActivity extends BaseMvpActivity<SearchListPresenter> implements SearchListContract.View {
    @BindView(R.id.view_toolbar)
    Toolbar mToolbar;

    private String mKey;
    private String mId;

    @BindView(R.id.gv_activity_content)
    GridView mGridView;
    private SearchKeyGridAdapter mSearchKeyGridAdapter;
    private SearchVideoTypeAdapter mSearchVideoTypeAdapter;
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
//        mKey = "123";
//        mId = "";
        mKey = intent.getStringExtra("key");
        mId = intent.getStringExtra("id");
        setToolbar(mToolbar, mKey);
        if (NetworkUtils.isNetworkAvailable()) {
            if (TextUtils.isEmpty(mId)) {
                mPresenter.getSearchDataByKey(mKey);
            } else {
                mPresenter.getSearchDataByVideoType(mId);
            }
        } else {

        }
    }

    @Override
    public void showSearchDataForKey(List<SearchKeyBean> searchKeyList) {
        mSearchKeyGridAdapter = new SearchKeyGridAdapter(mContext, searchKeyList);
        mGridView.setAdapter(mSearchKeyGridAdapter);
        mSearchKeyGridAdapter.notifyDataSetChanged();

    }

    @Override
    public void showSearchDataForVideoType(List<SearchVideoTypeBean> searchVideoTypeList) {
        mSearchVideoTypeAdapter = new SearchVideoTypeAdapter(mContext, searchVideoTypeList, mKey);
        mGridView.setAdapter(mSearchVideoTypeAdapter);
        mSearchVideoTypeAdapter.notifyDataSetChanged();;
    }
}
