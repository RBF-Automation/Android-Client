package com.rbfautomation.fragments;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rbfautomation.R;
import com.rbfautomation.data.CardItem;
import com.rbfautomation.views.CardListAdapter;

import java.util.ArrayList;


public class CardListViewFragment extends ListFragment {

    ArrayList<CardItem> mCardData;

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
        return v;
    }

}
