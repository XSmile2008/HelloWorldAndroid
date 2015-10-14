package com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.XSmile2008.helloworldandroid.app.R;

/*
Використовуючи BroadcastReciever ми можемо створити сервіс і запити в одному Activity а "ловити" данні в інших Activity
 */

public class ServicesActivity extends Activity {

    private TextView textViewTask1Status;
    private TextView textViewTask2Status;
    private TextView textViewTask3Status;

    private final String LOG_TAG = "MyService";

    private final int TASK1_CODE = 1;
    private final int TASK2_CODE = 2;
    private final int TASK3_CODE = 3;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_TASK = "task";//BroadcastReceiver
    public final static String PARAM_STATUS = "status";//BroadcastReceiver

    public final static int STATUS_START = 117;
    public final static int STATUS_FINISH = 118;

    BroadcastReceiver broadcastReceiver;//BroadcastReceiver
    public final static String BROADCAST_ACTION = "com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer";//BroadcastReceiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_layout);

        initViews();
        initBroadcastreceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void initViews(){
        textViewTask1Status = (TextView) findViewById(R.id.textViewTask1Status);
        textViewTask2Status = (TextView) findViewById(R.id.textViewTask2Status);
        textViewTask3Status = (TextView) findViewById(R.id.textViewTask3Status);
    }

    private void initBroadcastreceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(LOG_TAG, "onReceive: task = " + task + ", status = " + status);

                // Ловим сообщения о старте задач
                if (status  == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            textViewTask1Status.setText("Task1 start");
                            break;
                        case TASK2_CODE:
                            textViewTask2Status.setText("Task2 start");
                            break;
                        case TASK3_CODE:
                            textViewTask3Status.setText("Task3 start");
                            break;
                    }
                }

                // Ловим сообщения об окончании задач
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            textViewTask1Status.setText("Task1 finish, result = " + result);
                            break;
                        case TASK2_CODE:
                            textViewTask2Status.setText("Task2 finish, result = " + result);
                            break;
                        case TASK3_CODE:
                            textViewTask3Status.setText("Task3 finish, result = " + result);
                            break;
                    }
                }
            }
        };
        // создаем фильтр для BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(broadcastReceiver, intentFilter);
    }

    public void onButtonStartService(View view){//BroadcastReceiver
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer.MyService").putExtra(PARAM_TIME, 11).putExtra(PARAM_TASK, TASK1_CODE));
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer.MyService").putExtra(PARAM_TIME, 17).putExtra(PARAM_TASK, TASK2_CODE));
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer.MyService").putExtra(PARAM_TIME, 10).putExtra(PARAM_TASK, TASK3_CODE));
    }

    public void onButtonStopService(View view){
        stopService(new Intent(this, MyService.class));
    }


}
