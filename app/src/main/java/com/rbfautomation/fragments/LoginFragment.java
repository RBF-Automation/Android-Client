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

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.Settings;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.responses.GetTokenResponse;
import com.rbfautomation.network.responses.Response;

/**
 * Created by brian on 2/1/15.
 */
public class LoginFragment  extends Fragment implements IRbfFragment, NetworkManager.NetworkEventListener, View.OnClickListener {

    private TextView mUsername, mPassword;
    private Button mLogin;
    private INavigationEvents mNavigationEventHandler;

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

    public void setmNavigationEventHandler(INavigationEvents eventHandler) {
        mNavigationEventHandler = eventHandler;
    }

    private void login() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        NetworkManager networkManager = new NetworkManager(this, getActivity());
        networkManager.request(new GetTokenRequest(mUsername.getText().toString(), mPassword.getText().toString()));
    }

    private void saveToken(String token) {
        Settings settings = new Settings(getActivity());
        settings.setToken(token);
        mNavigationEventHandler.goToSplash();
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
