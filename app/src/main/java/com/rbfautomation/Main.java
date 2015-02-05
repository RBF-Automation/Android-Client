package com.rbfautomation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.rbfautomation.data.CardData;
import com.rbfautomation.fragments.CardListViewFragment;
import com.rbfautomation.fragments.IRbfFragment;
import com.rbfautomation.fragments.LoginFragment;
import com.rbfautomation.fragments.SplashFragment;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.EndSessionRequest;

import java.util.ArrayList;


public class Main extends ActionBarActivity implements INavigationEvents {

    public static final String FRAGMNET_ID = "mContent";

    private FragmentManager mFragmentManager;
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();

        if (savedInstanceState != null) {
            mContent = mFragmentManager.getFragment(savedInstanceState, FRAGMNET_ID);
            if (mContent instanceof IRbfFragment) {
                ((IRbfFragment)mContent).setmNavigationEventHandler(this);
            }

        } else {
            Settings settings = new Settings(this);

            if (settings.getToken() == null) {
                goToLogin();
            } else {
                goToSplash();
            }
        }

    }

    public static int getFragmnetContainer() {
        return android.R.id.content;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentManager.putFragment(outState, FRAGMNET_ID, mContent);
    }

    @Override
    public void logout() {
        Settings settings = new Settings(this);
        settings.setToken(null);
        NetworkManager networkManager = new NetworkManager(null, this);
        networkManager.request(new EndSessionRequest());
        goToLogin();
    }

    public void goToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setmNavigationEventHandler(this);
        mContent = loginFragment;
        mFragmentManager.beginTransaction().replace(getFragmnetContainer(), loginFragment).commit();
    }

    @Override
    public void goToCardListView(ArrayList<CardData> cards) {
        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setmNavigationEventHandler(this);
        fragment.setCardData(cards);
        mContent = fragment;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(0, android.R.animator.fade_out);
        ft.replace(getFragmnetContainer(), fragment).commit();

    }

    @Override
    public void goToSplash() {
        SplashFragment fragment = new SplashFragment();
        fragment.setmNavigationEventHandler(this);
        mContent = fragment;
        mFragmentManager.beginTransaction().replace(getFragmnetContainer(), fragment).commit();
    }

}
