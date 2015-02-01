package com.rbfautomation.views;

import android.content.Context;

import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.SwitchCardItem;

public class CardViewFactory {

	
	public static CardView getView(CardItem cardItem, Context context) {

		switch (cardItem.getType()) {
			case SwitchCardItem.TYPE:
				return populateSwitchCard((SwitchCardItem)cardItem, context);
	
			default:
				break;
		}

		return null;
	}

	public static CardView populateSwitchCard(SwitchCardItem cardItem, Context context) {
        SwitchCard card = new SwitchCard(context, cardItem);

		return card;
	}

}
