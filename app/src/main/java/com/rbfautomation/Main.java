package com.rbfautomation;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.rbfautomation.fragments.SplashFragment;


public class Main extends ActionBarActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();

        SplashFragment splashFragment = new SplashFragment();
        mFragmentManager.beginTransaction().add(android.R.id.content, splashFragment).commit();

    }

}
