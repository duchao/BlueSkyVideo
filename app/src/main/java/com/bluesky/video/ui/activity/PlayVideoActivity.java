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

import java.io.IOException;
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

    @BindView(R.id.progressbar)
    View mProgressBarView;
    @BindView(R.id.surfaceView)
    SurfaceView mSurfaceView;
    @BindView(R.id.media_controller_progress)
    SeekBar mSeekBar;
    @BindView(R.id.time)
    TextView mTimeView;
    @BindView(R.id.view_controll)
    View mControllView;
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

    private MediaPlayer mMediaPlayer;
    private boolean isTopay = false;

    private void playVideo(String videoUrl) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(3);
            mMediaPlayer.setDataSource(videoUrl);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.start();
                    }
                    mProgressBarView.setVisibility(View.GONE);
                    resetUpdataTime();

                    //过五秒开始初始化弹幕相关initDanmu(); 发送数字2

                }
            });
            mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    int level = UserInfo.getInstance().getUserType();
                    mSeekBar.setClickable(false);
                    mSeekBar.setEnabled(false);
                    if (level == 1) {
                        if (!isTopay) {
                            isTopay = true;
                            // 提示 试看结束请充值！然后打开支付页面intent.putExtra("type", "1");fninish;
                        }
                        return;
                    }
                    if (level == 4) {
                        mProgressBarView.setVisibility(View.VISIBLE);
                        // 3秒后，发送数字 3,执行toastProgress()
                        return;

                    }
                    if (level == 5) {
                        mProgressBarView.setVisibility(View.VISIBLE);
                        // 3秒后，发送数字 4， 执行toast2();
                        return;
                    }

                    if (level == 6) {
                        mProgressBarView.setVisibility(View.VISIBLE);
                        // 3秒后，发送数字 5，执行toastlanzuan();
                        return;
                    }
                    if (!isTopay) {
                        isTopay = true;
                        // 提示 试看结束请充值！然后打开支付页面intent.putExtra("type", "1");fninish;
                    }

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetUpdataTime() {
        // 开始更新时间，更新时间的间隔为1s
    }

    private void switchControllerState() {
        mControllView.setVisibility(mControllView.getVisibility() == View.VISIBLE ?
                View.GONE : View.VISIBLE);
    }

    private void initControl() {
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        int height = ScreenUtils.dip2px(mContext, 30);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), 0x7f020094);
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
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
        if (mSurfaceView != null) {
            mSurfaceView = null;
        }
        cancelTimer();
        System.gc();
    }

    private void cancelTimer() {

    }
}
