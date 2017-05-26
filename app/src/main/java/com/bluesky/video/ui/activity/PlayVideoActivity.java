package com.bluesky.video.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
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
import com.bluesky.video.utils.LogUtils;
import com.bluesky.video.utils.ScreenUtils;
import com.bluesky.video.utils.StringUtils;
import com.bluesky.video.utils.ToastUtils;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.tv_danumu_button)
    TextView mDanMuButton;

    DanmakuView mDanMuView;

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
        initDanMu();
        preparePlayVideo();
    }

    private void initDanMu() {
        mDanMuView = (DanmakuView) findViewById(R.id.view_dan_mu);
        mDanMuView.setVisibility(View.VISIBLE);
        mDanMuView.addItem(initItems(), true);
        mDanMuView.show();
    }

    private List<IDanmakuItem> initItems() {
        List<IDanmakuItem> itemsList = new ArrayList<>();
        String[] stringList = StringUtils.getText();
        Random stringRandom = new Random();
        int i = 0;
        int listLength = stringList.length;
        while(i < listLength) {
            String item = stringList[stringRandom.nextInt(listLength)];
            SpannableString ss = new SpannableString(item);
            ss.setSpan(new ForegroundColorSpan(-1), 0, item.length(), 33);
            ss.setSpan(new AbsoluteSizeSpan(50), 0, item.length(), 33);
            itemsList.add(new DanmakuItem(this, ss, mDanMuView.getWidth(), 0 ,0 ,0, 1.5f));
            i++;
        }
        return  itemsList;
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_play_video_thumb);
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

    private void preparePlayVideo() {
        mPresenter.preparePlayVideo();
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

        private int progressNow = 0;
        private int seek = 0;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seek = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            progressNow = seekBar.getProgress();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int level = UserInfo.getInstance().getUserType();
            mSeekBar.setProgress(progressNow);
            ToastUtils.showLongToast(mContext, StringUtils.getVipString(level));
            if (level >= 5) {
                mProgressBarView.setVisibility(View.GONE);
                if (mMediaPlayer != null) {
                    mMediaPlayer.pause();
                }
                mPresenter.stopControllbarUpdte();
                startPayProgress(progressNow);
            }

        }
    };

    private void startPayProgress(int progress) {
        int level = UserInfo.getInstance().getUserType();
        String str;
        if (level == 5) {
            str = "网络加载慢、建议开通云加速，播放影片不卡顿";
        } else if (level == 6) {
            str = "你已申请云加速权限、立即开启云加速，需要升级皇冠会员";
        } else {
            str = "云加速仅在色搜栏目提供、立即前往";
        }
        showPayProgressDialog(str, level, progress);
    }

    private void showPayProgressDialog(String str, final int level, final int progress) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage(str).setNegativeButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(level >= 7) {
                    //跳转到搜索的地方
                } else {
                    startPayActivity(false);
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mProgressBarView.setVisibility(View.GONE);

                if (mMediaPlayer != null) {
                    mMediaPlayer.start();
                }
                mSeekBar.setProgress(progress);
               mPresenter.startControllbarUpdate();
            }
        }).create().show();
    }

    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        mMediaPlayer = null;
        if (!isTopay) {
            if (UserInfo.getInstance().getUserType() == 1) {
                isTopay = true;
                startPayActivity(false);
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
        System.gc();
    }

    @Override
    public void playVideo() {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(3);
            mMediaPlayer.setDataSource(mVideoUrl);
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
                    mPresenter.startControllbarUpdate();
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
                            ToastUtils.showLongToast(mContext, "试看结束请充值！");
                            startPayActivity(false);
                        }
                    } else {
                        mProgressBarView.setVisibility(View.VISIBLE);
                        mPresenter.startCountDown(3);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controllbarUpdate() {
        updatePlayProgress();
        updateHadPlayTime();
    }

    @Override
    public void countDownCallback() {
        int level = UserInfo.getInstance().getUserType();
        if (level <= 4) {
            toastProgress();
        }else if (level == 5) {
            toast2();;
        } else if (level == 6) {
            taostLanZuan();
        }
    }

    private void toastProgress() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("网络不佳，高潮总在缓存后，请耐心等待...").setNegativeButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    private void toast2() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("网络不佳，建议开通云加速，播放不卡顿").setNegativeButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startPayActivity(true);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false).create().show();

    }

    private void taostLanZuan() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("网络不佳，建议开通云加速，播放不卡顿").setNegativeButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startPayActivity(true);
            }
        }).setCancelable(false).create().show();
    }

    private void updatePlayProgress() {
        int i = 0;
        int progress = 0;
        try {
            if (mMediaPlayer != null) {
                i = mMediaPlayer.getCurrentPosition();
            }
            progress = i * 100 / 3001082;
            if (progress > 100) {
                progress = 100;
            }
            mSeekBar.setProgress(progress);
            LogUtils.e("play", String.valueOf(i));
            LogUtils.e("play", String.valueOf(progress));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 这个地方要捕获异常,
    private void updateHadPlayTime() {
        int position = 0;
        try {
            if (mMediaPlayer != null) {
                position = mMediaPlayer.getCurrentPosition();
            }
            mTimeView.setText(StringUtils.getPlayTime(position) + "/" + mTimeLength);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPayActivity(boolean isNewTask) {
        Intent intent = new Intent(this, SplashActivity.class);
        if (isNewTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("type", "1");
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tv_danumu_button)
    void onClickDanmu() {
        if (mDanMuView != null) {
            mDanMuView.hide();
            mDanMuView.clear();
            mDanMuView.setVisibility(View.GONE);
            mDanMuButton.setVisibility(View.GONE);
        }
    }



}
