package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.XSmile2008.helloworldandroid.app.R;

/**
 * Created by vladstarikov on 09.01.15.
 */
public class LoginActivity extends Activity {

    private TextView textLogin;
    private TextView textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        textLogin = (TextView) findViewById(R.id.textViewLogin);
        textPassword = (TextView) findViewById(R.id.textViewPassword);

        textLogin.setText(getIntent().getStringExtra("login"));
        textPassword.setText(getIntent().getStringExtra("password"));

        Toast.makeText(this, "Congratulation!", Toast.LENGTH_SHORT).show();//Toast
    }

    public void onBackClick(View view){
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        onBackPressed();
    }

    public void onRememberMeClick(View view){
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);

        builder.setTitle("Remember Me?")
                .setMessage("Remember your login and password")
                .setCancelable(true)
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Save compete", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Your data is not save", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
