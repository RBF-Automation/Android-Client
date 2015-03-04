package com.rbfautomation.network.responses;

import com.rbfautomation.data.ActivityLogPreviewCardData;
import com.rbfautomation.data.CardData;
import com.rbfautomation.data.SwitchCardData;
import com.rbfautomation.data.UserTrackerCardData;
import com.rbfautomation.network.requests.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brian on 2/2/15.
 */
public class GetManifestResponse extends Response {

    private ArrayList<CardData> mCards;

    public GetManifestResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        try {
            //FOLLOW PACKET FORM
            mCards = new ArrayList<>();
            JSONArray jArray = new JSONArray(responseText);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);

                Integer type = (Integer) obj.get("type");

                switch (type) {
                    case SwitchCardData.TYPE:
                        mCards.add(new SwitchCardData(
                                (int) obj.get("id"),
                                (String) obj.get("name"),
                                (String) obj.get("btn_on"),
                                (String) obj.get("btn_off")
                        ));
                        break;

                    case ActivityLogPreviewCardData.TYPE:
                        mCards.add(new ActivityLogPreviewCardData(
                                (int) obj.get("id"),
                                (String) obj.get("name")
                        ));
                        break;

                    case UserTrackerCardData.TYPE:
                        mCards.add(new UserTrackerCardData(
                                (int) obj.get("id"),
                                (String) obj.get("name")
                        ));
                        break;
                }
            }

        } catch (JSONException e) {
            setError(ErrorCodes.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public ArrayList<CardData> getCards() {
        return mCards;
    }
}
