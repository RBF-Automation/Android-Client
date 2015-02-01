package com.rbfautomation;

import com.rbfautomation.data.CardItem;

import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public interface INavigationEvents {
    void logout();
    void goToCardListView(ArrayList<CardItem> cards);
    void goToSplash();
}
