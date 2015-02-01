package com.rbfautomation.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rbfautomation.R;
import com.rbfautomation.Settings;
import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.JsonDecoder;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.requests.Request;

import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public class LoginFragment  extends Fragment implements NetworkManager.NetworkEventListener, View.OnClickListener {

    private TextView mUsername, mPassword;
    private Button mLogin;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, null);
        mUsername = (TextView) v.findViewById(R.id.username);
        mPassword = (TextView) v.findViewById(R.id.password);
        mLogin = (Button) v.findViewById(R.id.login);

        mLogin.setOnClickListener(this);

        return v;
    }

    public void setupCardFragment(ArrayList<CardItem> cards) {
        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setCardData(cards);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    private void login() {
        NetworkManager networkManager = new NetworkManager(this, getActivity());
        networkManager.request(new GetTokenRequest(mUsername.getText().toString(), mPassword.getText().toString()));
    }

    private void saveToken(String token) {
        Settings settings = new Settings(getActivity());
        settings.setToken(token);

        SplashFragment fragment = new SplashFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            login();
        }
    }

    @Override
    public void onCompleteRequest(Request request, String response) {
        switch (request.getType()) {

            case GetTokenRequest.TYPE:
                String token = JsonDecoder.decodeToken(response);
                if (token != null) {
                    saveToken(token);
                } else {
                    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
