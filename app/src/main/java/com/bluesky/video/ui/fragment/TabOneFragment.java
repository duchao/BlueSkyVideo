package com.bluesky.video.ui.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.presenter.TabOneFragmentPresenter;
import com.bluesky.video.presenter.contract.TabOneFragmentContract;
import com.bluesky.video.ui.adapter.HomeVideGridAdapter;
import com.bluesky.video.utils.NetworkUtils;
import com.bluesky.video.utils.ScreenUtils;

import java.util.ArrayList;

import butterknife.BindView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by duchao on 2017/5/10.
 */

public class TabOneFragment extends BaseMvpFragment<TabOneFragmentPresenter>
        implements TabOneFragmentContract.View {


    @BindView(R.id.gv_fragment_content)
    GridViewWithHeaderAndFooter mGridView;
    private TextView mFootView;

    private HomeVideGridAdapter mHomeVideGridAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_one;
    }

    @Override
    protected void initEventAndData() {
        initHeadAndFooterView();
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getHomeVideoData();
        } else {
            // 网络错误提示
        }
    }

    private void initHeadAndFooterView() {
        mFootView = new TextView(mContext);
        mFootView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ScreenUtils.dip2px(mContext, 50)));
        mFootView.setGravity(Gravity.CENTER);
        mFootView.setTextColor(getResources().getColor(R.color.white));
        mFootView.setText(getText(R.string.fragment_tab_one_footer_text));
        mFootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 去充值
            }
        });
        mGridView.addFooterView(mFootView);
    }

    @Override
    public void showHomeVideoData(ArrayList<VideoBean> mVideoList) {
        mHomeVideGridAdapter = new HomeVideGridAdapter(mContext, mVideoList);
        mGridView.setAdapter(mHomeVideGridAdapter);
        mHomeVideGridAdapter.notifyDataSetChanged();
    }
}
