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

import com.rbfautomation.INavigationEvents;
import com.rbfautomation.R;
import com.rbfautomation.data.CardItem;
import com.rbfautomation.views.CardListAdapter;

import java.util.ArrayList;


public class CardListViewFragment extends ListFragment implements View.OnClickListener {

    ArrayList<CardItem> mCardData;
    private INavigationEvents mNavigationEventHandler;

    private Button mLogoutButton;

    public void setCardData(ArrayList<CardItem> cardData) {
        mCardData = cardData;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CardListAdapter cardListAdapter = new CardListAdapter(getActivity(), R.layout.view_card, mCardData);
        setListAdapter(cardListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_list_view, null);

        ActionBarActivity activity = (ActionBarActivity) getActivity();

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        activity.getSupportActionBar().setElevation(10);

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


        mLogoutButton = (Button) v.findViewById(R.id.logout_button);
        mLogoutButton.setOnClickListener(this);

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
}
