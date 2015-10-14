package com.XSmile2008.helloworldandroid.app.service.Binding;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.XSmile2008.helloworldandroid.app.R;

/*
Використовуючи BroadcastReciever ми можемо створити сервіс і запити в одному Activity а "ловити" данні в інших Activity
 */

public class ServicesActivity extends Activity {

    private TextView textViewTask1Status;
    private TextView textViewTaskInterval;

    private final String LOG_TAG = "MyService";
    public final String PARAM_TIME = "time";

    private boolean bound = false;
    private ServiceConnection serviceConnection;
    private Intent bindIntent;

    private MyService myService;
    long interval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_binding_layout);

        initViews();
        initServiceConnection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onButtonUnbindService(null);
    }

    private void initViews(){
        textViewTask1Status = (TextView) findViewById(R.id.textViewTask1Status);
        textViewTaskInterval = (TextView) findViewById(R.id.textViewTaskInterval);
    }

    private void initServiceConnection() {
        bindIntent = new Intent(this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG_TAG,"ServiceActivity.onServiceConnected");
                myService = ((MyService.MyBinder) service).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG,"ServiceActivity.onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onButtonStartService(View view) {
        startService(new Intent(this, MyService.class).putExtra(PARAM_TIME, 1000));
    }

    public void onButtonStopService(View view) {
        stopService(new Intent(this, MyService.class));
    }

    public void onButtonBindService(View view) {
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onButtonUnbindService(View view) {
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    public void onButtonServiceInc(View view) {
        if (!bound) return;
        interval = myService.incInterval(500);
        textViewTaskInterval.setText(String.valueOf(interval));
    }

    public void onButtonServiceDec(View view) {
        if (!bound) return;
        interval = myService.decInterval(500);
        textViewTaskInterval.setText(String.valueOf(interval));
    }

}
