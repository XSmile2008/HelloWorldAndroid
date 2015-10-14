package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.XSmile2008.helloworldandroid.app.R;


public class LogsActivity extends Activity {

    private final String TAG = "Logs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logs_layout);
    }



    public void onLogInfoClick(View view){
        Log.i(TAG,"Info level");
    }

    public void onLogErrorClick(View view){
        Log.e(TAG,"Error level");
    }

    public void onLogWarningClick(View view){
        Log.w(TAG,"Warning level");
    }

    public void onLogDebugClick(View view){
        Log.d(TAG,"Debug level");
    }

}
