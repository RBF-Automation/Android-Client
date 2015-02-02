package com.rbfautomation.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.Settings;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetManifestRequest;
import com.rbfautomation.network.requests.StartSessionRequest;
import com.rbfautomation.network.responses.GetManifestResponse;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.network.responses.StartSessionResponse;


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
    public void onCompleteRequest(Response response) {
        switch (response.getType()) {

            case StartSessionRequest.TYPE:
                if (((StartSessionResponse)response).sessionStarted()) {
                    mNetworkManager.request(new GetManifestRequest());
                } else {
                    mNavigationEventHandler.logout();
                }
                break;

            case GetManifestRequest.TYPE:

                if (!response.hasError()) {
                    mNavigationEventHandler.goToCardListView(((GetManifestResponse)response).getCards());
                } else {
                    Log.e("JSON", response.getError());
                    Toast.makeText(getActivity(), getResources().getText(R.string.manifest_error), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
