package com.rbfautomation.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rbfautomation.data.CardData;

import java.util.HashMap;
import java.util.List;


public class CardListAdapter extends ArrayAdapter<CardData> {

	private final Context mContext;
    private final CardView.CardViewEventHandler mCardEventHandler;
    private static HashMap<Long, CardView> mCache = new HashMap<Long, CardView>();
	
	public CardListAdapter(Context context, int resource, List<CardData> objects, CardView.CardViewEventHandler cardEventHandler) {
		super(context, resource, objects);
		mContext = context;
        mCardEventHandler = cardEventHandler;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//CardItem cardItem = getItem(position);
		
		//CardView card = (CardView) mCache.get(cardItem.getId());

		//if (card != null) {
		//	return card;
		//}
		
		CardView view = CardViewFactory.getView(getItem(position), mContext, mCardEventHandler);
		//mCache.put(cardItem.getId(), view);
		
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
