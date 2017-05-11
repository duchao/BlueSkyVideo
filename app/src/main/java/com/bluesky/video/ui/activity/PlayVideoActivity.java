package com.bluesky.video.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.base.BaseMvpActivity;
import com.bluesky.video.model.config.UserInfo;
import com.bluesky.video.presenter.PlayVideoPresenter;
import com.bluesky.video.presenter.contract.PlayVideoContract;
import com.bluesky.video.utils.ScreenUtils;
import com.bluesky.video.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @BindView(R.id.progressbar_playvideo)
    View mProgressBarView;
    @BindView(R.id.surfaceview_playvideo_display)
    SurfaceView mSurfaceView;
    @BindView(R.id.seekbar_controller_progress)
    SeekBar mSeekBar;
    @BindView(R.id.tv_controller_time)
    TextView mTimeView;
    @BindView(R.id.playvideo_controll_bar)
    View mControllView;
    @Override
    protected int getLayout() {
        return R.layout.activity_play_video;
    }

    @Override
    protected void initEventAndData() {
        mProgressBarView.setVisibility(View.VISIBLE);
        initData();
        initSurfaceView();
        initSeekBar();
        playVideo();
    }

    private void initData() {
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
    }

    private void initSurfaceView() {
        mSurfaceView .getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mProgressBarView.setVisibility(View.GONE);
            }
        });
        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchControllerState();
            }
        });
    }

    private void initSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        int height = ScreenUtils.dip2px(mContext, 30);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_play_video_start);
        Bitmap thumb = Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(thumb);
        canvas.drawBitmap(bitmap,
                new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                new Rect(0, 0, thumb.getWidth(), thumb.getHeight()),
                null);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), thumb);
        mSeekBar.setThumb(drawable);
        mSeekBar.setThumbOffset(-3);
    }

    private MediaPlayer mMediaPlayer;
    private boolean isTopay = false;

    private void playVideo() {
        mPresenter.playVideo(mVideoUrl, mSurfaceView);
    }

    private void resetUpdataTime() {
        // 开始更新时间，更新时间的间隔为1s
    }

    private void switchControllerState() {
        mControllView.setVisibility(mControllView.getVisibility() == View.VISIBLE ?
                View.GONE : View.VISIBLE);
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

    private void startPayProgress(int param) {
        int i = UserInfo.getInstance().getUserType();
        String str;
        if (i == 5) {
            str = "网络加载慢、建议开通云加速，播放影片不卡顿";
        } else if (i == 6) {
            str = "你已申请云加速权限、立即开启云加速，需要升级皇冠会员";
        } else {
            str = "云加速仅在色搜栏目提供、立即前往";
        }

        // 显示弹窗对话框,确定，或者取消

    }

    private void updatePlayProgress() {
        int i = 0;
        try {
            if (mMediaPlayer != null) {
                i = mMediaPlayer.getCurrentPosition();
            }
            int j = i * 100 / 3001082;
            i = j;
            if (j > 100) {
                i = 100;
            }
            mSeekBar.setProgress(i);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePlayTime() {
        int i = 0;
        int j = 0;
        try {
            if (mMediaPlayer != null) {
                i = mMediaPlayer.getDuration();
                j = mMediaPlayer.getCurrentPosition();
            }
            mTimeView.setText(getPlayTime(j, i));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String formatPlayTime(long paramLong) {
        return new SimpleDateFormat("mm:ss").format(new Date(paramLong));
    }

    private String getPlayTime(int paramInt1, int paramInt2) {
        String str = "00:00";
        try {
            str = formatPlayTime((long)paramInt1);
            return str;
        } catch(Exception localException) {
            localException.printStackTrace();
        }
        return "00";
    }

    private void toast2() {
        // dialog
        //  当前网络不佳、建议开通云加速,播放影片不卡顿

    }

    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        mMediaPlayer = null;
        if (!isTopay) {
            if (UserInfo.getInstance().getUserType() == 1) {
                //试看结束请充值
                isTopay = true;
                //开始支付的应用
            }
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.onDestroy();
        if (mSurfaceView != null) {
            mSurfaceView = null;
        }
        System.gc();
    }



    @Override
    public void showProgressbar() {
        mProgressBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressbar() {
        mProgressBarView.setVisibility(View.GONE);
    }

    @Override
    public void disableSeekbar() {
        mSeekBar.setClickable(false);
        mSeekBar.setEnabled(false);
    }

    @Override
    public void updateSeekbarProgress(int progress) {
        mSeekBar.setProgress(progress);
    }

    @Override
    public void updateControllTime(String time) {
        mTimeView.setText(time + "/" + mTimeLength);
    }
}
