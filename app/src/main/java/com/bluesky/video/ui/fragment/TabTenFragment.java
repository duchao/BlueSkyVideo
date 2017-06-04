package com.bluesky.video.ui.fragment;

import android.widget.GridView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.ForumBean;
import com.bluesky.video.presenter.TabTenFragmentPresenter;
import com.bluesky.video.presenter.contract.TabTenFragmentContract;
import com.bluesky.video.ui.adapter.TabTenGridAdapter;
import com.bluesky.video.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by duchao on 2017/6/2.
 */

public class TabTenFragment extends BaseMvpFragment<TabTenFragmentPresenter> implements TabTenFragmentContract.View {
    @BindView(R.id.gv_fragment_content)
    GridView mGridView;
    private TabTenGridAdapter mTabTenGridAdapter;
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_ten;
    }

    @Override
    protected void initEventAndData() {
        String videoType = "59";
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getForumData(videoType);
        } else {

        }

    }

    @Override
    public void showForumData(List<ForumBean> forumList) {
        mTabTenGridAdapter = new TabTenGridAdapter(mContext, forumList);
        mGridView.setAdapter(mTabTenGridAdapter);
        mTabTenGridAdapter.notifyDataSetChanged();
    }
}
