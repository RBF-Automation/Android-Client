package com.rbfautomation.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rbfautomation.R;
import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.JsonDecoder;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.ManifestRequest;
import com.rbfautomation.network.requests.Request;

import java.util.ArrayList;


public class SplashFragment extends Fragment implements NetworkManager.NetworkEventListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        NetworkManager networkManager = new NetworkManager(this, activity.getBaseContext());
        networkManager.request(new ManifestRequest());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash, null);
        return v;
    }

    public void setupCardFragment(ArrayList<CardItem> cards) {
        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setCardData(cards);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    @Override
    public void onCompleteRequest(Request request, String response) {
        switch (request.getType()) {

            case ManifestRequest.TYPE:
                ArrayList<CardItem> cards = JsonDecoder.decodeManifest(response);
                setupCardFragment(cards);
                break;
        }
    }
}
