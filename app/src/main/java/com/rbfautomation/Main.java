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
import com.rbfautomation.network.ISessionContext;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.EndSessionRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.responses.Response;

import java.util.ArrayList;


public class Main extends ActionBarActivity implements IGlobalEvents {

    public static final String FRAGMENT_ID = "mContent";

    private FragmentManager mFragmentManager;
    private Fragment mContent;
    private Settings mSettings;
    private RbfSessionContext mSessionContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();
        mSettings = new Settings(this);

        if (savedInstanceState != null) {
            mContent = mFragmentManager.getFragment(savedInstanceState, FRAGMENT_ID);
            if (mContent instanceof IRbfFragment) {
                ((IRbfFragment)mContent).setGlobalEventHandler(this);
            }

        } else {


            if (mSettings.getToken() == null) {
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
        mFragmentManager.putFragment(outState, FRAGMENT_ID, mContent);
    }

    @Override
    public void logout() {
        mSettings.setToken(null);
        //Special case for logout. We dont want this request to be recoverable, or attempt to start a session
        NetworkManager networkManager = new NetworkManager(null, this, new ISessionContext() {
            @Override
            public Request getSessionStartRequest() {
                return null;
            }
            @Override
            public boolean requestRecoverable(Response response) {
                return false;
            }
            @Override
            public boolean validateSessionStart(Response response) {
                return false;
            }
        });
        networkManager.request(new EndSessionRequest());
        goToLogin();
    }

    public void goToLogin() {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setGlobalEventHandler(this);
        mContent = loginFragment;
        mFragmentManager.beginTransaction().replace(getFragmnetContainer(), loginFragment).commit();
    }

    @Override
    public void goToCardListView(ArrayList<CardData> cards) {
        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setGlobalEventHandler(this);
        fragment.setCardData(cards);
        mContent = fragment;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(0, android.R.animator.fade_out);
        ft.replace(getFragmnetContainer(), fragment).commit();

    }

    @Override
    public void goToSplash() {
        SplashFragment fragment = new SplashFragment();
        fragment.setGlobalEventHandler(this);
        mContent = fragment;
        mFragmentManager.beginTransaction().replace(getFragmnetContainer(), fragment).commit();
    }

    @Override
    public ISessionContext getSessionContext() {
        return new RbfSessionContext(mSettings.getToken());
    }

}
