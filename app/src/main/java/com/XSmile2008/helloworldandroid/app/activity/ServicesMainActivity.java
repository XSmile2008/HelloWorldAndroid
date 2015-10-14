package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import com.XSmile2008.helloworldandroid.app.R;

public class ServicesMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_main_layout);
    }

    public void onButtonServicesPendingIntentClick(View view) {
        startActivity(new Intent(this, com.XSmile2008.helloworldandroid.app.service.PendingIntentVer.ServicesActivity.class));
    }

    public void onButtonServicesBroadcastReceiverClick(View view) {
        startActivity(new Intent(this, com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer.ServicesActivity.class));
    }

    public void onButtonServicesBindingClick(View view) {
        startActivity(new Intent(this, com.XSmile2008.helloworldandroid.app.service.Binding.ServicesActivity.class));
    }

    public void onButtonServicesNotificationsClick(View view) {
        startActivity(new Intent(this, com.XSmile2008.helloworldandroid.app.service.Notifications.ServicesActivity.class));
    }

}
