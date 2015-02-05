package com.rbfautomation;

import com.rbfautomation.data.CardData;

import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public interface INavigationEvents {
    void logout();
    void goToCardListView(ArrayList<CardData> cards);
    void goToSplash();
}
