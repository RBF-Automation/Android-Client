package com.rbfautomation.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.Settings;
import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.JsonDecoder;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.ManifestRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.requests.StartSessionRequest;

import java.util.ArrayList;


public class SplashFragment extends Fragment implements NetworkManager.NetworkEventListener {

    NetworkManager mNetworkManager;
    private INavigationEvents mNavigationEventHandler;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Settings settings = new Settings(activity);

        mNetworkManager = new NetworkManager(this, activity.getBaseContext());
        mNetworkManager.request(new StartSessionRequest(settings.getToken()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_fragment, null);
        return v;
    }


    public void setmNavigationEventHandler(INavigationEvents eventHandler) {
        mNavigationEventHandler = eventHandler;
    }

    @Override
    public void onCompleteRequest(Request request, String response) {
        switch (request.getType()) {

            case StartSessionRequest.TYPE:
                if (JsonDecoder.decodeSessionStart(response)) {
                    mNetworkManager.request(new ManifestRequest());
                } else {
                    mNavigationEventHandler.logout();

                }
                break;

            case ManifestRequest.TYPE:
                ArrayList<CardItem> cards = JsonDecoder.decodeManifest(response);
                if (cards != null) {
                    mNavigationEventHandler.goToCardListView(cards);
                } else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.manifest_error), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
