package com.bluesky.video.ui.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.model.config.UserInfo;
import com.bluesky.video.presenter.TabTwoFragmentPresenter;
import com.bluesky.video.presenter.contract.TabTwoFragmentContract;
import com.bluesky.video.ui.adapter.TabTwoGridAdapter;
import com.bluesky.video.utils.NetworkUtils;
import com.bluesky.video.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by duchao on 2017/5/26.
 */

public class TabTwoFragment extends BaseMvpFragment<TabTwoFragmentPresenter> implements TabTwoFragmentContract.View {
    @BindView(R.id.gv_fragment_content)
    GridViewWithHeaderAndFooter mGridView;
    private TextView mFootView;

    private List<VideoBean> mVideoList;
    private TabTwoGridAdapter mTabTwoGridAdapter;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_two;
    }

    @Override
    protected void initEventAndData() {
        initViews();
        mVideoList = new ArrayList<>();
        int type = UserInfo.getInstance().getUserType();
        String videoType = "8";
        if (type != 1) {
            videoType = "2";
        }
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
