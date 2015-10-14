package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.XSmile2008.helloworldandroid.app.MyProgressBarAsyncTask;
import com.XSmile2008.helloworldandroid.app.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsyncTaskActivity extends Activity {

    private MyProgressBarAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_layout);
        Log.d("AsyncTask","create AsyncTaskActivity"+this.hashCode());

        task = (MyProgressBarAsyncTask) getLastNonConfigurationInstance();
        if (task == null){
            task = new MyProgressBarAsyncTask();
            Log.d("AsyncTask","create MyProgressBarAsyncTask"+task.hashCode());

        }
        task.setActivity(this);

    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return task;
    }

    @Override
    protected void onDestroy() {
        Log.d("AsyncTask","destroy AsyncTaskActivity"+this.hashCode());
        super.onDestroy();
    }

    public void onButtonStartAsyncTaskClick(View view) throws ExecutionException, InterruptedException {
        task.execute();

        try {
            String text = task.get(2, TimeUnit.SECONDS);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        } catch (TimeoutException e) {
            //e.printStackTrace();
            Log.e("AsyncTask","No answer from thread more then 2 seconds");
        }

    }

    public void onButtonStopAsyncTaskClick(View view){
        task.cancel(false);//if true may cause errors because system terminate thread
    }

    public void onButtonStatusAsyncTaskClick(View view){
        Toast.makeText(this, task.getStatus().toString(), Toast.LENGTH_SHORT).show();
    }

}
