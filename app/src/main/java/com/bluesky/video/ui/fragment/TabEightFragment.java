package com.bluesky.video.ui.fragment;

import android.widget.GridView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.PinDaoBean;
import com.bluesky.video.presenter.TabEightFragmentPresenter;
import com.bluesky.video.presenter.contract.TabEightFragmentContract;
import com.bluesky.video.ui.adapter.TabEightGridAdapter;
import com.bluesky.video.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by duchao on 2017/5/26.
 */

public class TabEightFragment extends BaseMvpFragment<TabEightFragmentPresenter> implements TabEightFragmentContract.View {
    @BindView(R.id.gv_fragment_content)
    GridView mGridView;

    private TabEightGridAdapter mTabEightGridAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_eight;
    }

    @Override
    protected void initEventAndData() {
        String parentId = "53";
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getPinDaoData(parentId);
        } else {

        }

    }

    @Override
    public void showPinDaoData(List<PinDaoBean> mPinDaoList) {
        mTabEightGridAdapter = new TabEightGridAdapter(mContext, mPinDaoList);
        mGridView.setAdapter(mTabEightGridAdapter);
        mTabEightGridAdapter.notifyDataSetChanged();
    }
}
