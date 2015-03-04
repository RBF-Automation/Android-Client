package com.rbfautomation.network.responses;

import com.rbfautomation.data.ActivityLogEvent;
import com.rbfautomation.network.requests.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brian on 2/5/15.
 */
public class GetActivtyListResponse extends Response {

    private ArrayList<ActivityLogEvent> mEvents;

    public GetActivtyListResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        try {

            JSONObject obj = new JSONObject(responseText);

            if (obj.getBoolean(RESULT)) {
                mEvents = new ArrayList<>();
                String data = obj.getString("data");
                JSONArray jArray = new JSONArray(data);

                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject logItem = jArray.getJSONObject(i);

                    mEvents.add(new ActivityLogEvent(
                        logItem.getString("user"),
                        logItem.getString("message"),
                        logItem.getString("time")
                    ));


                }

            } else {
                setError(obj.getInt(ERROR_CODE), obj.getString(MESSAGE));
            }


        } catch (JSONException e) {
            setError(ErrorCodes.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public ArrayList<ActivityLogEvent> getActivityEventList() {
        return mEvents;
    }
}
