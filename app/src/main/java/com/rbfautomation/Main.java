package com.rbfautomation;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.rbfautomation.fragments.LoginFragment;
import com.rbfautomation.fragments.SplashFragment;


public class Main extends ActionBarActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();

        Settings settings = new Settings(this);

        if (settings.getToken() == null) {
            LoginFragment loginFragment = new LoginFragment();
            mFragmentManager.beginTransaction().add(android.R.id.content, loginFragment).commit();
        } else {
            SplashFragment splashFragment = new SplashFragment();
            mFragmentManager.beginTransaction().add(android.R.id.content, splashFragment).commit();
        }



    }

}
