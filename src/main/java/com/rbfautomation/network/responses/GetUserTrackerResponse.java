package com.rbfautomation.network.responses;

import android.util.Log;

import com.rbfautomation.data.UserTrackerItem;
import com.rbfautomation.network.requests.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brian on 2/2/15.
 */
public class GetUserTrackerResponse extends Response {

    private ArrayList<UserTrackerItem> mItems;

    public GetUserTrackerResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        Log.e("string", responseText);
        try {

            JSONObject obj = new JSONObject(responseText);

            if (obj.getBoolean(RESULT)) {
                mItems = new ArrayList<>();
                String data = obj.getString("data");
                JSONArray jArray = new JSONArray(data);

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject logItem = jArray.getJSONObject(i);

                    mItems.add(new UserTrackerItem(
                            logItem.getString("username"),
                            logItem.getBoolean("isHome"),
                            logItem.getString("status")
                    ));


                }

            } else {
                setError(obj.getInt(ERROR_CODE), obj.getString(MESSAGE));
            }


        } catch (JSONException e) {
            setError(ErrorCodes.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public ArrayList<UserTrackerItem> getUserTrackerData() {
        return mItems;
    }
}

