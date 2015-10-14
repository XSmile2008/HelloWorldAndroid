package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.XSmile2008.helloworldandroid.app.R;


public class IntentFilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_layout);
    }

    public void onButtonExplicitCallClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onButtonImlicitCallClick(View view){
        Intent intent = new Intent("android.intent.action.MAIN");//if two activity has same name will be call dialog window, and you can choose what activity to run
        startActivity(intent);
    }

    public void onButtonOpenContactsClick(View view){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
        startActivity(intent);
    }
}
