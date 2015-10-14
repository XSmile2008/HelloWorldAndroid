package com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by vladstarikov on 29.01.15.
 */
public class MyService extends Service {

    private final String LOG_TAG = "MyService";
    private ExecutorService executorService;
    private Object someRes;

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG,"MyService.onBind");
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"MyService.onCreate");
        executorService = Executors.newFixedThreadPool(3);//amount of threads
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"MyService.onDestroy");
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService.onStartCommand");
        readFlags(flags);

        int time = intent.getIntExtra(ServicesActivity.PARAM_TIME, 1);
        int task = intent.getIntExtra(ServicesActivity.PARAM_TASK, 0);

        MyRun myRun = new MyRun(time,startId,task);
        executorService.execute(myRun);

        //return super.onStartCommand(intent, flags, startId);
        //return START_NOT_STICKY;//not be restart after kill
        //return START_STICKY;//will be restart after kill, but callService call will be lost if not complete by stopSelf()
        return START_REDELIVER_INTENT;//will be restart, and system give to Service all startService calls, that not succesful end by stopSelf()
    }



    private void readFlags(int flags){
        if ((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) Log.d(LOG_TAG,"START_FLAG_REDELIVERY");
        if ((flags&START_FLAG_RETRY) == START_FLAG_RETRY) Log.d(LOG_TAG,"START_FLAG_RETRY");
    }

    class MyRun implements Runnable {
        int time;
        int startId;
        int task;

        public MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(LOG_TAG,"MyRun#"+startId+".MyRun time = "+time+" created");
        }

        @Override
        public void run() {
            Intent intent = new Intent(ServicesActivity.BROADCAST_ACTION);
            Log.d(LOG_TAG,"MyRun#"+startId+".run");
            try {
                sendBroadcast(intent.putExtra(ServicesActivity.PARAM_TASK, task)
                        .putExtra(ServicesActivity.PARAM_STATUS, ServicesActivity.STATUS_START));

                TimeUnit.SECONDS.sleep(time);

                sendBroadcast(intent.putExtra(ServicesActivity.PARAM_RESULT, time*1000)
                        .putExtra(ServicesActivity.PARAM_STATUS, ServicesActivity.STATUS_FINISH));

                Log.d(LOG_TAG, "MyRun#" + startId + " sleep for " + time + " seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Log.d(LOG_TAG,"MyRun#"+startId+".someRes = "+someRes.getClass());
            } catch (NullPointerException e) {
                Log.e(LOG_TAG,"MyRun#"+startId+".someRes = null");
            }

            stop();
        }

        public void stop() {
            Log.d(LOG_TAG,"MyRun#"+startId+".stop, stopSelf("+startId+")");
            stopSelf(startId);
            //boolean b = stopSelfResult(startId);
        }
    }
}
