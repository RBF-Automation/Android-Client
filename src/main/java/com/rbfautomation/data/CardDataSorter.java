package com.rbfautomation.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by brian on 2/6/15.
 */
public class CardDataSorter {

    public interface CardDataSorterEventHandler {
        void saveOrder(ArrayList<Integer> cards);
    }

    private ArrayList<Integer> mOrder;
    private CardDataSorterEventHandler mEventHandler;

    public CardDataSorter(ArrayList<Integer> order, CardDataSorterEventHandler eventHandler) {
        mOrder = order;
        mEventHandler = eventHandler;
    }

    private void saveOrder() {
        mEventHandler.saveOrder(mOrder);
    }

    /**
     * Maps an array of cards to the order pulled form the settings.
     * Returns the same input, just in a different order.
     * @param cards
     * @return
     */
    public ArrayList<CardData> map(ArrayList<CardData> cards) {

        CardData[] mapped = new CardData[mOrder.size()];

        ArrayList<CardData> unMapped = new ArrayList<>();
        for (CardData card : cards) {
            if (mOrder.contains(card.getRemoteId())) {
                mapped[mOrder.indexOf(card.getRemoteId())] = card;
            } else {
                unMapped.add(card);
            }
        }

        ArrayList<CardData> realMapped = new ArrayList<>(Arrays.asList(mapped));
        realMapped.addAll(unMapped);
        realMapped.removeAll(Collections.singleton(null));
        setOrder(realMapped);

        return realMapped;
    }

    /**
     * Saves the card order.
     * @param cards
     */
    public void setOrder(ArrayList<CardData> cards) {
        mOrder = new ArrayList<>(cards.size());
        for (CardData card : cards) {
            mOrder.add(card.getRemoteId());
        }
        saveOrder();
    }

}
