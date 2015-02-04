package com.rbfautomation.views;

import android.content.Context;

import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.SwitchCardItem;

public class CardViewFactory {

	
	public static CardView getView(CardItem cardItem, Context context, CardView.CardViewEventHandler cardEventHandler) {

		switch (cardItem.getType()) {
			case SwitchCardItem.TYPE:
				return populateSwitchCard((SwitchCardItem)cardItem, context, cardEventHandler);
	
			default:
				break;
		}

		return null;
	}

	public static CardView populateSwitchCard(SwitchCardItem cardItem, Context context, CardView.CardViewEventHandler cardEventHandler) {
        SwitchCard card = new SwitchCard(context, cardItem, cardEventHandler);

		return card;
	}

}
