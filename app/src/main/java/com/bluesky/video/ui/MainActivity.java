package com.bluesky.video.ui;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseActivity;
import com.bluesky.video.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.search)
    TextView mSearchView;

    RelativeLayout mLinearLayout = null;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        Object localObject2 = new RelativeLayout.LayoutParams(-1, ScreenUtils.dip2px(this, 54.0F));
        ((RelativeLayout.LayoutParams)localObject2).addRule(12);
        int i = 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.search)
    public void onSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
