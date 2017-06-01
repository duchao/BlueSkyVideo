package com.bluesky.video.ui.fragment;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.GridView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpFragment;
import com.bluesky.video.model.bean.PinDaoBean;
import com.bluesky.video.presenter.TabNineFragmentPresenter;
import com.bluesky.video.presenter.contract.TabNineFragmentContract;
import com.bluesky.video.ui.adapter.TabNineGridAdapter;
import com.bluesky.video.ui.view.SearchButtonView;
import com.bluesky.video.utils.NetworkUtils;
import com.bluesky.video.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by duchao on 2017/6/1.
 */

public class TabNineFragment extends BaseMvpFragment<TabNineFragmentPresenter> implements TabNineFragmentContract.View {
    @BindView(R.id.gv_fragment_tab_nine_search_content)
    GridView mGridView;
    @BindView(R.id.sbv_fragment_tab_nine_search)
    SearchButtonView mSearchButtonView;
    @BindView(R.id.et_fragment_tab_nine_search_txt)
    EditText mSearchTxt;

    private TabNineGridAdapter mGridAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tab_nine;
    }

    @Override
    protected void initEventAndData() {
        String parentId = "64";
        if (NetworkUtils.isNetworkAvailable()) {
            mPresenter.getPinDaoData(parentId);
        }
    }

    private void initViews() {

    }

    @OnClick(R.id.sbv_fragment_tab_nine_search)
    void onSearch() {
        String searchTxt = mSearchTxt.getText().toString();
        if (TextUtils.isEmpty(searchTxt)) {
            ToastUtils.showToast(mContext, R.string.fragment_tab_nine_search_txt_no_empety);
        } else {
            // 关闭键盘，打开搜索activity
        }
    }

    @Override
    public void showPinDaoData(List<PinDaoBean> pinDaoList) {
        mGridAdapter = new TabNineGridAdapter(mContext, pinDaoList);
        mGridView.setAdapter(mGridAdapter);
        mGridAdapter.notifyDataSetChanged();
    }
}
