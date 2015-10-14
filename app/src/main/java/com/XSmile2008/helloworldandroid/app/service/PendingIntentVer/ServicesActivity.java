package com.XSmile2008.helloworldandroid.app.service.PendingIntentVer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.XSmile2008.helloworldandroid.app.R;

/*
Використовуючи PendingIntent сервіс може повернути данні тільки в те Activity з якого поступив запит
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
    public final static String PARAM_PINTENT = "pendingIntent";//PendingIntent
    public final static String PARAM_RESULT = "result";

    public final static int STATUS_START = 117;
    public final static int STATUS_FINISH = 118;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_layout);
        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//Pendingintent
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(LOG_TAG," requestCode = "+requestCode+", resultCode = "+resultCode);

        // Ловим сообщения о старте задач
        if (resultCode == STATUS_START) {
            switch (requestCode) {
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
        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
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

    public void initViews(){
        textViewTask1Status = (TextView) findViewById(R.id.textViewTask1Status);
        textViewTask2Status = (TextView) findViewById(R.id.textViewTask2Status);
        textViewTask3Status = (TextView) findViewById(R.id.textViewTask3Status);
    }

    public void onButtonStartService(View view){//PendingIntent
        PendingIntent pendingIntent;
        pendingIntent = createPendingResult(TASK1_CODE, new Intent(), 0);
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.PendingIntentVer.MyService").putExtra(PARAM_TIME, 11).putExtra(PARAM_PINTENT, pendingIntent));
        pendingIntent = createPendingResult(TASK2_CODE, new Intent(), 0);
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.PendingIntentVer.MyService").putExtra(PARAM_TIME, 17).putExtra(PARAM_PINTENT, pendingIntent));
        pendingIntent = createPendingResult(TASK3_CODE, new Intent(), 0);
        startService(new Intent("com.XSmile2008.helloworldandroid.app.service.PendingIntentVer.MyService").putExtra(PARAM_TIME, 10).putExtra(PARAM_PINTENT, pendingIntent));
    }

    public void onButtonStopService(View view){
        stopService(new Intent(this, MyService.class));
    }

}
