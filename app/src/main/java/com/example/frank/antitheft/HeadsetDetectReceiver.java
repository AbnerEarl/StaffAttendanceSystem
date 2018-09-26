package com.example.frank.antitheft;

/**
 * Created by Frank on 2018/3/28.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class HeadsetDetectReceiver extends BroadcastReceiver {

    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;
    //更新歌词的定时器
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;

    public MediaPlayer mPlayer= new MediaPlayer();;

    private Context contextH;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        contextH=context;
        if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
            if (intent.hasExtra("state")) {
                int state = intent.getIntExtra("state", 0);
                if (state == 1) {
                    Toast.makeText(context, "插入耳机", Toast.LENGTH_SHORT).show();
                    if (mPlayer.isPlaying()){
                        mPlayer.stop();
                    }

                } else if(state == 0){
                    Toast.makeText(context, "拔出耳机", Toast.LENGTH_SHORT).show();
                    beginLrcPlay();
                }
            }
        }
    }





    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay(){
        //mPlayer = new MediaPlayer();
        try {
            // mPlayer.setDataSource(getAssets().openFd("woggh.mp3").getFileDescriptor());
            mPlayer=MediaPlayer.create(contextH,R.raw.jingbao);
            //准备播放歌曲监听
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //准备完毕
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    if(mTimer == null){
                        mTimer = new Timer();
                        //mTask = new contextH.LrcTask();
                        //0mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
                    }
                }
            });
            //歌曲播放完毕监听
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    //stopLrcPlay();
                    //mp.seekTo(0);
                    //mPlayer.seekTo(0);
                    mPlayer.start();
                }
            });
            //准备播放歌曲
            mPlayer.prepare();
            //开始播放歌曲
            mPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止展示歌曲
     */
    public void stopLrcPlay(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }




}

