package com.rbfautomation.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rbfautomation.data.CardData;

import java.util.ArrayList;
import java.util.HashMap;


public class CardListAdapter extends BaseAdapter {

    private final Context mContext;
    private final CardView.CardViewEventHandler mCardEventHandler;
    private ArrayList<CardData> mCardData;
    private static HashMap<Integer, CardView> mCache = new HashMap<>();

    public CardListAdapter(Context context, ArrayList<CardData> cardItems, CardView.CardViewEventHandler cardEventHandler) {
        mContext = context;
        mCardEventHandler = cardEventHandler;
        mCardData = cardItems;
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public CardData getItem(int index) {
        return mCardData.get(index);
    }

    @Override
    public int getCount() {
        return mCardData.size();
    }

    public void setCardDataAndRefresh(ArrayList<CardData> cardItems) {
        mCardData = cardItems;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        CardView card = mCache.get(getItem(position).getRemoteId());

        if (card != null) {
            return card;
        }

        CardView view = CardViewFactory.getView(getItem(position), mContext, mCardEventHandler);
        mCache.put(getItem(position).getRemoteId(), view);

        return view;
        
    }
    
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mCache.clear();
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        return false;
    }
    

}
