package com.rbfautomation.network.responses;

import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.SwitchCardItem;
import com.rbfautomation.network.requests.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brian on 2/2/15.
 */
public class GetManifestResponse extends Response {

    private ArrayList<CardItem> mCards;

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
                    case SwitchCardItem.TYPE:
                        mCards.add(new SwitchCardItem(
                                (int) obj.get("id"),
                                (String) obj.get("name"),
                                (String) obj.get("btn_on"),
                                (String) obj.get("btn_off")
                        ));
                        break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            setError("error parsing json");
        }
    }

    public ArrayList<CardItem> getCards() {
        return mCards;
    }
}
