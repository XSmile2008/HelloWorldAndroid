package com.XSmile2008.helloworldandroid.app.fragmet;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.XSmile2008.helloworldandroid.app.R;

/**
 * Created by vladstarikov on 24.01.15.
 */
public class SecondFragment extends Fragment {

    public static final String TAG = "SecondFragmentTag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

}
