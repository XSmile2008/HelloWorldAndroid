package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.XSmile2008.helloworldandroid.app.R;
import com.XSmile2008.helloworldandroid.app.service.BroadcastReceiverVer.ServicesActivity;


/**
 * Created by vladstarikov on 07.01.15.
 */
public class MainActivity extends Activity {
    private TextView textView;
    private EditText editText;
    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        login = (EditText) findViewById(R.id.editTextLogin);
        password = (EditText) findViewById(R.id.editTextPassword);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                textView.setText("Vlad ne nyashka");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            finish();
            System.exit(0);
            return true;
        }

        if (id == R.id.action_log) {
            startActivity(new Intent(this, LogsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view){
        textView.setText("Olya nyashka");
    }

    public void onButtonIncClick(View view){
        int i = Integer.parseInt(editText.getText().toString());
        editText.setText(String.valueOf(++i));
    }

    public void onButtonDecClick(View view){
        int i = Integer.parseInt(editText.getText().toString());
        editText.setText(String.valueOf(--i));
    }

    public void onButtonLoginClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login",login.getText().toString());
        intent.putExtra("password",password.getText().toString());
        startActivity(intent);
    }

    public void onButtonSetLanguageClick(View view){
        startActivityForResult(new Intent(this, LanguageActivity.class),1);
    }

    public void onButtonLogsClick(View view){
        startActivity(new Intent(this, LogsActivity.class));
    }

    public void onButtonIntentFilterClick(View view){
        startActivity(new Intent(this, IntentFilterActivity.class));
    }

    public void onButtonListViewClick(View view){
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void onButtonFragmentslick(View view){
        startActivity(new Intent(this, FragmentsActivity.class));
    }

    public void onButtonAsyncTaskClick(View view){
        startActivity(new Intent(this, AsyncTaskActivity.class));
    }

    public void onButtonServicesClick(View view){
        startActivity(new Intent(this, ServicesMainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String s = data.getStringExtra("Language");
                Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
