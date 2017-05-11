package com.bluesky.video.presenter;

import android.media.MediaPlayer;
import android.view.SurfaceView;

import com.bluesky.video.base.RxPresenter;
import com.bluesky.video.model.config.UserInfo;
import com.bluesky.video.model.http.RetrofitHelper;
import com.bluesky.video.presenter.contract.PlayVideoContract;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by duchao on 2017/5/10.
 */

public class PlayVideoPresenter extends RxPresenter<PlayVideoContract.View>
        implements PlayVideoContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    private MediaPlayer mMediaPlayer;
    private boolean isTopay = false;
    private Timer mUpdateTimer;

    @Inject
    public PlayVideoPresenter(RetrofitHelper retrofitHelper) {
        mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void playVideo(final String videoUrl, final SurfaceView surfaceView) {
        Disposable rxSubsction = Flowable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        test(videoUrl, surfaceView);
                    }
                });

    }




    public void test(String videoUrl, SurfaceView surfaceView) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(3);
            mMediaPlayer.setDataSource(videoUrl);
            mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.setDisplay(surfaceView.getHolder());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.start();
                    }
                    mView.dismissProgressbar();
                    timerUpdate();
                    //resetUpdataTime();
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
                    mView.disableSeekbar();
                    if (level == 1) {
                        if (!isTopay) {
                            isTopay = true;
                            // 提示 试看结束请充值！然后打开支付页面intent.putExtra("type", "1");fninish;
                        }
                        return;
                    }
                    if (level == 4) {
                        mView.showProgressbar();
                        // 3秒后，发送数字 3,执行toastProgress()
                        return;

                    }
                    if (level == 5) {
                        mView.showProgressbar();
                        // 3秒后，发送数字 4， 执行toast2();
                        return;
                    }

                    if (level == 6) {
                        mView.showProgressbar();
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

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer = null;
        }
        cancelTimer();
    }

    private void cancelTimer() {
        if (mUpdateTimer != null) {
            mUpdateTimer.cancel();
        }
    }

    // 需要rxjava重写
    private void resetUpdateTimer() {
        mUpdateTimer = new Timer();
        mUpdateTimer.schedule(new TimerTask() {
            public void run() {

            }
        }, 0L, 1000L);
    }

    private void timerUpdate() {
        Disposable rxSubscrition = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        updatePlayProgress();
                        updatePlayTime();
                    }
                });
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
            mView.updateSeekbarProgress(i);
            return;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void updatePlayTime() {
        int j = 0;
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.getDuration();
                j = mMediaPlayer.getCurrentPosition();
            }
            mView.updateControllTime(getPlayTime(j));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getPlayTime(int paramInt1) {
        long l = paramInt1;
        try {
            String str = formatPlayTime(l);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "00";
    }

    private String formatPlayTime(long paramLong) {
        return new SimpleDateFormat("mm:ss").format(new Date(paramLong));
    }

}
