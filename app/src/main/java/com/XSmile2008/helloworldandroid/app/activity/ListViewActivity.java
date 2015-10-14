package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.XSmile2008.helloworldandroid.app.PhoneModel;
import com.XSmile2008.helloworldandroid.app.R;
import com.XSmile2008.helloworldandroid.app.adapter.PhoneModelAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {

    private ListView listView;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,initData());
        listView = (ListView) findViewById(R.id.listViewPhones);
        listView.setAdapter(adapter);

        PhoneModelAdapter phoneModelAdapter = new PhoneModelAdapter(this, initData2());
        listView2 = (ListView) findViewById(R.id.listViewPhones2);
        listView2.setAdapter(phoneModelAdapter);
    }

    private List<String> initData(){
        List<String> list = new ArrayList<String>();

        list.add("Оля");
        list.add("Дуже гарна");
        list.add("Дуже розумна");
        list.add("Дуже добра");
        list.add("Дуже няшна");
        list.add("Дуже смачна");
        list.add("Дуже гарно готує");

        return list;
    }

    private List<PhoneModel> initData2(){
        List<PhoneModel> list = new ArrayList<PhoneModel>();

        list.add(new PhoneModel(1,"iPhone 3gs",500));
        list.add(new PhoneModel(1,"iPhone 4",600));
        list.add(new PhoneModel(1,"iPhone 4s",750));
        list.add(new PhoneModel(1,"iPhone 5",1000));
        list.add(new PhoneModel(1,"iPhone 5s",1200));

        return list;
    }
}
