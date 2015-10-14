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
public class FirstFragment extends Fragment {

    public static final String TAG = "FirstFragmentTag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }

}
