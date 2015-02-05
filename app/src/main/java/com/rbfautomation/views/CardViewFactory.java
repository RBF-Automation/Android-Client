package com.rbfautomation.views;

import android.content.Context;

import com.rbfautomation.data.ActivityLogPreviewCardData;
import com.rbfautomation.data.CardData;
import com.rbfautomation.data.SwitchCardData;

public class CardViewFactory {

	
	public static CardView getView(CardData cardData, Context context, CardView.CardViewEventHandler cardEventHandler) {

		switch (cardData.getType()) {
			case SwitchCardData.TYPE:
                return new SwitchCard(context, (SwitchCardData) cardData, cardEventHandler);

            case ActivityLogPreviewCardData.TYPE:
                return new ActivityLogPreviewCard(context, (ActivityLogPreviewCardData) cardData, cardEventHandler);

			default:
				break;
		}

		return null;
	}


}
