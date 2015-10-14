package com.XSmile2008.helloworldandroid.app.service.Notifications;

import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import com.XSmile2008.helloworldandroid.app.R;
import com.XSmile2008.helloworldandroid.app.activity.LoginActivity;

public class MyService extends Service {
    NotificationManager notificationManager;

    private final int NOTIFICATION_ID = 117;//Myr


    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);//если по старому способу запускать из Activity
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotif();
        mySendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotif() {
        // 1-я часть
        Notification notif = new Notification(R.drawable.ic_launcher, "Text in status bar",
                System.currentTimeMillis());

        // 3-я часть
        Intent intent = new Intent(this, ServicesActivity.class);
        intent.putExtra(ServicesActivity.FILE_NAME, "somefile");
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 2-я часть
        //notif.setLatestEventInfo(this, "Notification's title", "Notification's text", pIntent);

        // ставим флаг, чтобы уведомление пропало после нажатия
        notif.flags |= Notification.FLAG_AUTO_CANCEL;

        // отправляем
        notificationManager.notify(1, notif);
    }

    void mySendNotif() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.ic_launcher))
                .setTicker("Login to unlock new content")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Login")
                .setContentText("Click to Login")
                .setProgress(100,10,true);


        Notification notification = builder.build();
        //notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
        //notification.flags = notification.flags | Notification.FLAG_ONGOING_EVENT | Notification.FLAG_INSISTENT;//not close
        notification.sound = Uri.parse("android.resource://com.XSmile2008.helloworldandroid.app/" + R.raw.chidori);

        long[] vibrate = new long[]{1000, 750, 1000, 1500};
        //notification.vibrate = vibrate;

        //notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}