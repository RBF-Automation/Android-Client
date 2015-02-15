package com.rbfautomation.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rbfautomation.IGlobalEvents;
import com.rbfautomation.R;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.responses.GetTokenResponse;
import com.rbfautomation.network.responses.Response;

/**
 * Created by brian on 2/1/15.
 */
public class LoginFragment  extends Fragment implements NetworkManager.NetworkEventListener, View.OnClickListener {

    public interface LoginEvents {
        public void saveToken(String token);
    }

    private TextView mUsername, mPassword;
    private Button mLogin;
    private IGlobalEvents mGlobalEventHandler;
    private LoginEvents mLoginEvents;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLoginEvents = (LoginEvents) activity;
            mGlobalEventHandler = (IGlobalEvents) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()  + " must implement LoginEvents and IGlobalEvents");
        }
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

    private void login() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        NetworkManager networkManager = new NetworkManager(this, getActivity(), mGlobalEventHandler.getSessionContext());
        networkManager.request(new GetTokenRequest(mUsername.getText().toString(), mPassword.getText().toString()));
    }

    private void saveToken(String token) {
        mLoginEvents.saveToken(token);
        mGlobalEventHandler.goToSplash();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            login();
        }
    }

    @Override
    public void onCompleteRequest(Response response) {
        switch (response.getType()) {

            case GetTokenRequest.TYPE:
                if (!response.hasError()) {
                    saveToken(((GetTokenResponse)response).getToken());
                } else {
                    Toast.makeText(getActivity(), response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
