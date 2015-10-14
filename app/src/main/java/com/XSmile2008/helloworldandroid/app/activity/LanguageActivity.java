package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.XSmile2008.helloworldandroid.app.R;

/**
 * Created by vladstarikov on 11.01.15.
 */
public class LanguageActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_layout);
    }

    public void onLanguageButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        switch (view.getId()){
            case R.id.buttonEnglish:
                intent.putExtra("Language", "English");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.buttonUkraine:
                intent.putExtra("Language", "Ukraine");
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.buttonRussian:
                intent.putExtra("Language", "Russian");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
