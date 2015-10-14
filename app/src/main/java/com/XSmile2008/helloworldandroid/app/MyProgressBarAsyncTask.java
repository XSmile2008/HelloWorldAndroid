package com.XSmile2008.helloworldandroid.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vladstarikov on 27.01.15.
 */
public class MyProgressBarAsyncTask extends AsyncTask<Void , Integer, String> {

    private int progressBarValue = 0;
    private ProgressBar progressBar;
    private TextView textViewAsyncTaskProgress;
    private Activity activity;

    @Override
    protected String doInBackground(Void... params) {
        while (progressBarValue < 100){
            if (isCancelled()) return null;
            try {
                progressBarValue++;
                publishProgress(progressBarValue);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "Hello world!!!";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(activity.getApplicationContext(),"Task started", Toast.LENGTH_SHORT).show();
        Log.d("AsyncTask","Task started"+" Task="+this.hashCode()+" Activity="+activity.hashCode());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(activity.getApplicationContext(), "Task finished", Toast.LENGTH_SHORT).show();
        Log.d("AsyncTask","Task finished"+" Task="+this.hashCode()+" Activity="+activity.hashCode());
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        textViewAsyncTaskProgress.setText(values[0].toString());
        Log.d("AsyncTask","Progress:"+values[0].toString()+" Task="+this.hashCode()+" Activity="+activity.hashCode());
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        progressBar = (ProgressBar) activity.findViewById(R.id.progressBarAsyncTask);
        textViewAsyncTaskProgress = (TextView) activity.findViewById(R.id.textViewAsyncTaskProgress);
    }
}
