package com.XSmile2008.helloworldandroid.app.service.Notifications;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.XSmile2008.helloworldandroid.app.R;

public class ServicesActivity extends Activity {

    public final static String FILE_NAME = "filename";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_notification_layout);

        TextView tv = (TextView) findViewById(R.id.textViewNotificationService);

        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName))
            tv.setText(fileName);
    }

    public void onButtonNotificationServiceStart(View v) {
        startService(new Intent(this, MyService.class));
    }


    public void onButtonNotificationServiceStop(View v) {
        stopService(new Intent(this, MyService.class));
    }
}