package com.XSmile2008.helloworldandroid.app.service.Binding;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by vladstarikov on 29.01.15.
 */
public class MyService extends Service {

    private final String LOG_TAG = "MyService";

    MyBinder myBinder;

    private Timer timer;
    private TimerTask timerTask;
    long interval = 1000;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"MyService.onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(LOG_TAG,"MyService.onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(LOG_TAG,"MyService.onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        myBinder.getService();
        Log.d(LOG_TAG,"MyService.onCreate");
        timer = new Timer();
        schedule();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"MyService.onDestroy");
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService.onStartCommand");
        //readFlags(flags);
        //schedule();
        return super.onStartCommand(intent, flags, startId);
    }*/

    private void schedule() {
        if(timerTask != null) timerTask.cancel();
        if(interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(LOG_TAG,"timerTask.run("+interval+")");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    public long incInterval(int inc){
        interval+=inc;
        schedule();
        return interval;
    }

    public long decInterval(int dec){
        interval-=dec;
        if (interval<0) interval = 0;
        schedule();
        return interval;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            Log.e(LOG_TAG,"Try getService()...");
            return MyService.this;
        }
    }
}
