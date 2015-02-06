package com.rbfautomation.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.data.CardData;
import com.rbfautomation.network.NetworkManager;
import com.rbfautomation.network.requests.GetUserInformationRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.GetUserInformationResponse;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.views.CardListAdapter;
import com.rbfautomation.views.CardView;

import java.util.ArrayList;


public class CardListViewFragment extends ListFragment implements IRbfFragment, View.OnClickListener, NetworkManager.NetworkEventListener, CardView.CardViewEventHandler {

    ArrayList<CardData> mCardData;
    private INavigationEvents mNavigationEventHandler;
    private NetworkManager mNetworkManager;
    private TextView mUsernameText;
    private Button mLogoutButton;

    public void setCardData(ArrayList<CardData> cardData) {
        mCardData = cardData;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mCardData = savedInstanceState.getParcelableArrayList("CardData");
        }

        CardListAdapter cardListAdapter = new CardListAdapter(getActivity(), R.layout.card_view, mCardData, this);
        setListAdapter(cardListAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mCardData != null) {
            outState.putParcelableArrayList("CardData", mCardData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_list_fragment, null);

        ActionBarActivity activity = (ActionBarActivity) getActivity();

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        DrawerLayout mDrawerLayout = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle;
        mDrawerToggle = new ActionBarDrawerToggle(
                activity,  mDrawerLayout, toolbar,
                R.string.abc_action_bar_home_description, R.string.abc_action_bar_home_description
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        mUsernameText = (TextView) v.findViewById(R.id.username_text);
        mLogoutButton = (Button) v.findViewById(R.id.logout_button);
        mLogoutButton.setOnClickListener(this);

        mNetworkManager = new NetworkManager(this, getActivity());
        mNetworkManager.request(new GetUserInformationRequest());

        return v;
    }

    public void setmNavigationEventHandler(INavigationEvents eventHandler) {
        mNavigationEventHandler = eventHandler;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout_button:
                mNavigationEventHandler.logout();
                break;
        }
    }

    @Override
    public void onCompleteRequest(Response response) {
        if (!response.hasError()) {
            switch (response.getType()) {
                case GetUserInformationRequest.TYPE:
                    mUsernameText.setText(((GetUserInformationResponse) response).getUsername());
                    break;
            }
        } else {
            switch (response.getErrorCode()) {
                case ErrorCodes.NO_RESPONSE_FROM_SERVER:
                    Toast.makeText(getActivity(), response.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onCardNetworkError(int errorCode, String errorMessage) {
        switch (errorCode) {
            case ErrorCodes.NOT_LOGGED_IN:
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                mNavigationEventHandler.logout();
                break;

            case ErrorCodes.NO_RESPONSE_FROM_SERVER:
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
