package com.XSmile2008.helloworldandroid.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.XSmile2008.helloworldandroid.app.R;
import com.XSmile2008.helloworldandroid.app.fragmet.FirstFragment;
import com.XSmile2008.helloworldandroid.app.fragmet.SecondFragment;

public class FragmentsActivity extends FragmentActivity {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_layout);

        fragmentManager = getSupportFragmentManager();//якщо імпортували не з Support то getFragmentManager

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    public void onButtonsFragmentClick(View view){
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.buttonAddFragment:{
                if(fragmentManager.findFragmentByTag(FirstFragment.TAG) == null && fragmentManager.findFragmentByTag(SecondFragment.TAG) == null) {
                    fragmentTransaction.add(R.id.fragmentConteiner, firstFragment, FirstFragment.TAG);
                }
                    break;
            }

            case R.id.buttonRemoveFragment:{
                if(fragmentManager.findFragmentByTag(FirstFragment.TAG) != null) {
                    fragmentTransaction.remove(firstFragment);
                }
                if(fragmentManager.findFragmentByTag(SecondFragment.TAG) != null) {
                    fragmentTransaction.remove(secondFragment);
                }
                break;
            }

            case R.id.buttonReplaceFragment:{
                if(fragmentManager.findFragmentByTag(FirstFragment.TAG) != null) {
                    fragmentTransaction.replace(R.id.fragmentConteiner,secondFragment,SecondFragment.TAG);
                }
                if(fragmentManager.findFragmentByTag(SecondFragment.TAG) != null) {
                    fragmentTransaction.replace(R.id.fragmentConteiner,firstFragment,FirstFragment.TAG);
                }
                break;
            }

        }

        //fragmentTransaction.addToBackStack(null);//якщо ми хочемо вертатися по стеку викликів фрагментів клавішою Back


        fragmentTransaction.commit();
    }

}
