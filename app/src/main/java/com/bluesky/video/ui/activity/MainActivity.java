package com.bluesky.video.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.presenter.MainPresenter;
import com.bluesky.video.presenter.contract.MainContract;
import com.bluesky.video.ui.adapter.MainViewPagerAdapter;
import com.bluesky.video.ui.fragment.TabEightFragment;
import com.bluesky.video.ui.fragment.TabNineFragment;
import com.bluesky.video.ui.fragment.TabSevenFragment;
import com.bluesky.video.ui.fragment.TabSixFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.tl_main_tab)
    TabLayout mFooterTabLayout;
    @BindView(R.id.vp_main_content)
    ViewPager mContentViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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
        setToolbar(mToolbar,"");
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
        mFragmentList.add(new TabNineFragment());
        mFragmentList.add(new TabSixFragment());
        mFragmentList.add(new TabSevenFragment());
        mFragmentList.add(new TabEightFragment());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_main_head:
                Intent intent = new Intent(this, MineActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
