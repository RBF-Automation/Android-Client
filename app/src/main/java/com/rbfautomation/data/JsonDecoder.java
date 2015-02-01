package com.rbfautomation.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brian on 1/31/15.
 */
public class JsonDecoder {

    public static ArrayList<CardItem> decodeManifest(String response) {
        try {

            ArrayList<CardItem> cards = new ArrayList<>();
            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);

                Integer type = (Integer) obj.get("type");

                switch (type) {
                    case SwitchCardItem.TYPE:
                        cards.add(new SwitchCardItem(
                                (int) obj.get("id"),
                                (String) obj.get("name"),
                                (String) obj.get("btn_on"),
                                (String) obj.get("btn_off")
                        ));
                        break;
                }


            }

            return cards;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
