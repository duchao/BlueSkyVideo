package com.bluesky.video.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseSimpleActivity;
import com.bluesky.video.model.config.UserInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by duchao on 2017/5/25.
 */

public class MineActivity extends BaseSimpleActivity{

    @BindView(R.id.tv_mine_username)
    TextView mUserName;
    @BindView(R.id.tv_mine_touxian_content)
    TextView mTouXian;
    @BindView(R.id.tv_mine_tequan_content)
    TextView mTeQuan;
    @BindView(R.id.tv_mine_pay_vip)
    TextView mPayVip;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initEventAndData() {
        setToolbar(mToolbar, R.string.mine_title);
        mToolbar.setTitle(R.string.mine_title);
        initUserInfo();

    }

    private void initUserInfo() {
        String userId = UserInfo.getInstance().getUserId();
        mUserName.setText("用户ID: u_1069" + userId);
        int level = UserInfo.getInstance().getUserType();
        switch (level) {
            case 1:
                mUserName.setText("普通会员");
                mTeQuan.setText("观看体验视频");
                break;
            case 2:
                mUserName.setText("黄金会员");
                mTeQuan.setText("观看黄金区视频");
                break;
            case 3:
                mUserName.setText("钻石会员");
                mTeQuan.setText("观看钻石区视频");
                break;
            case 4:
                mUserName.setText("黄钻会员");
                mTeQuan.setText("观看黄钻区视频");
                break;
            case 5:
                mUserName.setText("黑金会员");
                mTeQuan.setText("观看黑金区视频");
                break;
            case 6:
                mUserName.setText("蓝钻会员");
                mTeQuan.setText("观看蓝钻区视频");
                break;
            case 7:
                mUserName.setText("皇冠会员");
                mTeQuan.setText("观看皇冠区视频");
                break;
            case 8:
                mUserName.setText("顶级会员");
                mTeQuan.setText("开通快进缓存功能");
                break;
            case 9:
                mUserName.setText("隐藏会员");
                mTeQuan.setText("海外vpn大片");
                break;
            default:
                mUserName.setText("终极会员");
                mTeQuan.setText("完全无限制享受");
                break;
        }
    }

    @OnClick(R.id.tv_mine_pay_vip)
    void onPayVip() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("type", "1");
        startActivity(intent);
    }
}
