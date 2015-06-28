package com.rbfautomation.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rbfautomation.data.CardData;

import java.util.ArrayList;


public class CardListAdapter extends BaseAdapter {

    private final Context mContext;
    private final CardView.CardViewEventHandler mCardEventHandler;
    private ArrayList<CardData> mCardData;
    //private static HashMap<Long, CardView> mCache = new HashMap<Long, CardView>();
    
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
        
        //CardItem cardItem = getItem(position);
        
        //CardView card = (CardView) mCache.get(cardItem.getId());

        //if (card != null) {
        //    return card;
        //}
        
        CardView view = CardViewFactory.getView(getItem(position), mContext, mCardEventHandler);
        //mCache.put(cardItem.getId(), view);
        
        return view;
        
    }
    
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        //mCache.clear();
    }
    
    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        return false;
    }
    

}
