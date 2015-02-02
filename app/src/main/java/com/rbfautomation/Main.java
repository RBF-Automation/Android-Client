package com.rbfautomation;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.rbfautomation.data.CardItem;
import com.rbfautomation.fragments.CardListViewFragment;
import com.rbfautomation.fragments.LoginFragment;
import com.rbfautomation.fragments.SplashFragment;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.EndSessionRequest;

import java.util.ArrayList;


public class Main extends ActionBarActivity implements INavigationEvents {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();

        Settings settings = new Settings(this);

        if (settings.getToken() == null) {
            goToLogin();
        } else {
            goToSplash();
        }

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
        mFragmentManager.beginTransaction().replace(android.R.id.content, loginFragment).commit();
    }

    @Override
    public void goToCardListView(ArrayList<CardItem> cards) {
        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setmNavigationEventHandler(this);
        fragment.setCardData(cards);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

    }

    @Override
    public void goToSplash() {
        SplashFragment fragment = new SplashFragment();
        fragment.setmNavigationEventHandler(this);
        mFragmentManager.beginTransaction().replace(android.R.id.content, fragment).commit();
    }
}
