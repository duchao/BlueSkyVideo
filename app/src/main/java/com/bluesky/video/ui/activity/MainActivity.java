package com.bluesky.video.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.presenter.MainPresenter;
import com.bluesky.video.presenter.contract.MainContract;
import com.bluesky.video.ui.adapter.MainViewPagerAdapter;
import com.bluesky.video.ui.fragment.TabOneFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tl_main_tab)
    TabLayout mFooterTabLayout;
    @BindView(R.id.vp_main_content)
    ViewPager mContentViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    private ArrayList<Fragment> mFragmentList;

    private ArrayList<String> mTabTitleList;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initFragment();
        initTabTitle();
        MainViewPagerAdapter mMainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),
                mFragmentList, mTabTitleList);
        mContentViewPager.setAdapter(mMainViewPagerAdapter);
        mFooterTabLayout.setupWithViewPager(mContentViewPager);
        initTabIcon();
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TabOneFragment());
        mFragmentList.add(new TabOneFragment());
        mFragmentList.add(new TabOneFragment());
        mFragmentList.add(new TabOneFragment());
    }

    private void initTabTitle() {
        mTabTitleList = new ArrayList<>();
        mTabTitleList.add("1");
        mTabTitleList.add("2");
        mTabTitleList.add("3");
        mTabTitleList.add("4");
    }

    private void initTabIcon() {
        one = mFooterTabLayout.getTabAt(0);
        two = mFooterTabLayout.getTabAt(1);
        three = mFooterTabLayout.getTabAt(2);
        four = mFooterTabLayout.getTabAt(3);
        one.setIcon(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        two.setIcon(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        three.setIcon(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        four.setIcon(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
    }


}
