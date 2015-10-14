package com.XSmile2008.helloworldandroid.app.fragmet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.XSmile2008.helloworldandroid.app.R;

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = (Button) getActivity().findViewById(R.id.buttonSayHelloWorld);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView textView1 = (TextView) getActivity().findViewById(R.id.textViewFragment1);
                TextView textView2 = (TextView) getActivity().findViewById(R.id.textViewFragment2);
                textView1.setText("Hello");
                textView2.setText("World");
            }
        });
    }
}
