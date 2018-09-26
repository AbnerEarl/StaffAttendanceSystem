package com.example.frank.antitheft.Service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

/**
 * PROJECT_NAME:AntiTheft
 * PACKAGE_NAME:com.example.frank.antitheft.Service
 * USER:Frank
 * DATE:2018/3/31
 * TIME:17:57
 * DAY_NAME_FULL:星期六
 * DESCRIPTION:On the description and function of the document
 **/
public class GrayService extends Service {
    private final static int GRAY_SERVICE_ID = 1001;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //API < 18,此方法能有效地隐藏notification的图标
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else {
            Intent intent1 = new Intent(this, GrayInnerService.class);
            startService(intent1);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }
        return super.onStartCommand(intent, flags, startId);
    }


    //给API >= 18 的平台上做灰色保护手段
    public class GrayInnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }


        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();

            return super.onStartCommand(intent, flags, startId);
        }
    }


}