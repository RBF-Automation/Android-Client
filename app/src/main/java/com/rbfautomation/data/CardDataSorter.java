package com.rbfautomation.data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by brian on 2/6/15.
 */
public class CardDataSorter {

    public interface CardDataSorterEventHandler {
        public void saveOrder(ArrayList<Integer> cards);
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

    public ArrayList<CardData> map(ArrayList<CardData> cards) {

        ArrayList<CardData> mapped = new ArrayList<>();
        while (mapped.size() < cards.size()) mapped.add(null);

        ArrayList<CardData> unMapped = new ArrayList<>();

        for (CardData card : cards) {
            if (mOrder.contains(card.getType())) {
                mapped.add(mOrder.indexOf(card.getType()), card);
            } else {
                unMapped.add(card);
            }
        }

        mapped.removeAll(Collections.singleton(null));
        mapped.addAll(unMapped);

        setOrder(mapped);

        return mapped;
    }

    public void setOrder(ArrayList<CardData> cards) {
        mOrder = new ArrayList<>(cards.size());
        for (CardData card : cards) {
            mOrder.add(card.getType());
        }
        saveOrder();
    }

}
