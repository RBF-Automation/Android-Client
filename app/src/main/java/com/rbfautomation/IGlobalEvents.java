package com.rbfautomation;

import com.rbfautomation.data.CardData;
import com.rbfautomation.data.CardDataSorter;
import com.rbfautomation.network.ISessionContext;

import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public interface IGlobalEvents {
    public void logout();
    public void goToCardListView(ArrayList<CardData> cards);
    public void goToSplash();
    public ISessionContext getSessionContext(); //Move
    public ArrayList<Integer> getCardOrder(); //MOVE
    public CardDataSorter.CardDataSorterEventHandler getCardOrderSaver(); //MOVE

}
