package com.bluesky.video.ui.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.model.config.UserInfo;
import com.bluesky.video.presenter.TabThreeFragmentPresenter;
import com.bluesky.video.presenter.contract.TabThreeFragmentContract;
import com.bluesky.video.ui.adapter.TabTwoGridAdapter;
import com.bluesky.video.utils.NetworkUtils;
import com.bluesky.video.utils.ScreenUtils;

import java.util.ArrayList;

import butterknife.BindView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by duchao on 2017/5/26.
 */

public class TabThreeFragment extends BaseMvpFragment<TabThreeFragmentPresenter> implements TabThreeFragmentContract.View {
    @BindView(R.id.gv_fragment_content)
    GridViewWithHeaderAndFooter mGridView;
    private TextView mFootView;

    private TabTwoGridAdapter mTabTwoGridAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_three;
    }

    @Override
    protected void initEventAndData() {
        initViews();
        int type = UserInfo.getInstance().getUserType();
        String videoType = "3";
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getVideoData(videoType);
        } else {

        }

    }

    private void initViews(){
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
    public void showVideoData(ArrayList<VideoBean> mVideoList) {
        mTabTwoGridAdapter = new TabTwoGridAdapter(mContext, mVideoList);
        mGridView.setAdapter(mTabTwoGridAdapter);
        mTabTwoGridAdapter.notifyDataSetChanged();
    }
}
