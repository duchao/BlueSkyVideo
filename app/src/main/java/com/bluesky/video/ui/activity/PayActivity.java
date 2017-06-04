package com.bluesky.video.ui.activity;

import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.presenter.PayActivityPresenter;
import com.bluesky.video.presenter.contract.PayActivityContract;

import butterknife.BindView;

/**
 * Created by duchao on 2017/6/4.
 */

public class PayActivity extends BaseMvpActivity<PayActivityPresenter> implements PayActivityContract.View {
    @BindView(R.id.iv_pay_vipsmallpic)
    ImageView mVipSamllPic;
    @BindView(R.id.tv_pay_viptype)
    TextView mVipeType;
    @BindView(R.id.tv_pay_vipmoney)
    TextView mVipMoney;
    @BindView(R.id.tv_pay_okpay)
    TextView mOkPay;
    @BindView(R.id.rb_pay_weixinsele)
    RadioButton mWeixinSele;
    @BindView(R.id.rb_pay_alisele)
    RadioButton mAliSele;
    @BindView(R.id.iv_pay_image_view)
    ImageView mPayImageView;
    @BindView(R.id.iv_pay_cancel_view)
    ImageView mCancelView;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initEventAndData() {

    }
}
