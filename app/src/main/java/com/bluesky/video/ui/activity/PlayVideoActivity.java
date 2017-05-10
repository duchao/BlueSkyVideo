package com.bluesky.video.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.presenter.PlayVideoPresenter;
import com.bluesky.video.presenter.contract.PlayVideoContract;
import com.bluesky.video.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by duchao on 2017/5/10.
 */

public class PlayVideoActivity extends BaseMvpActivity<PlayVideoPresenter>
        implements PlayVideoContract.View{

    private String mVideoUrl;
    private String mLy;//不知道啥意思
    private String mZs;
    private String mTimeLength;

    @BindView(R.id.progressbar)
    private View mProgressBarView;
    @BindView(R.id.surfaceView)
    private SurfaceView surfaceView;
    @BindView(R.id.media_controller_progress)
    private SeekBar seekBar;
    @BindView(R.id.time)
    private TextView time;
    @BindView(R.id.view_controll)
    private View controllView;
    @Override
    protected int getLayout() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mVideoUrl = intent.getStringExtra("url");
        try {
            mVideoUrl = StringUtils.getVideoString(mVideoUrl);
        } catch(Exception e) {
            e.printStackTrace();
        }
        mLy = intent.getStringExtra("ly");
        mZs = intent.getStringExtra("zs");
        mTimeLength = intent.getStringExtra("time");
        if(TextUtils.isEmpty(mTimeLength)) {
            mTimeLength = "62:28";
        }
        mProgressBarView.setVisibility(View.VISIBLE);
        seekBar.setOnSeekBarChangeListener(mSeekBarListener);


    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
