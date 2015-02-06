package com.rbfautomation.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.Settings;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetManifestRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.requests.StartSessionRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.GetManifestResponse;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.network.responses.StartSessionResponse;


public class SplashFragment extends Fragment implements IRbfFragment, NetworkManager.NetworkEventListener, View.OnClickListener {

    NetworkManager mNetworkManager;
    private INavigationEvents mNavigationEventHandler;
    private Button mRetryButton;
    private Request mLastRequest;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Settings settings = new Settings(activity);

        mNetworkManager = new NetworkManager(this, activity.getBaseContext());
        mNetworkManager.startSession(settings.getToken());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_fragment, null);
        mRetryButton = (Button) v.findViewById(R.id.retry_button);
        mRetryButton.setOnClickListener(this);
        return v;
    }


    public void setmNavigationEventHandler(INavigationEvents eventHandler) {
        mNavigationEventHandler = eventHandler;
    }

    @Override
    public void onCompleteRequest(Response response) {
        if (!response.hasError()) {
            switch (response.getType()) {

                case StartSessionRequest.TYPE:
                    if (((StartSessionResponse) response).sessionStarted()) {
                        mNetworkManager.request(new GetManifestRequest());
                    } else {
                        mNavigationEventHandler.logout();
                    }
                    break;

                case GetManifestRequest.TYPE:

                    if (!response.hasError()) {
                        mNavigationEventHandler.goToCardListView(((GetManifestResponse) response).getCards());
                    } else {
                        Log.e("ERROR", response.getErrorMessage());
                        Toast.makeText(getActivity(), getResources().getText(R.string.manifest_error), Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        } else {
            switch (response.getErrorCode()) {
                case ErrorCodes.NO_RESPONSE_FROM_SERVER:
                    Toast.makeText(getActivity(), response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    mLastRequest = response.getRequest();
                    mRetryButton.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry_button:
                if (mLastRequest != null) {
                    mNetworkManager.request(mLastRequest);
                }
                break;
        }
    }
}
