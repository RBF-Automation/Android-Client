package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 2/2/15.
 */
public class StartSessionResponse extends Response {

    private boolean mSessionStarted;

    public StartSessionResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        try {
            JSONObject obj = new JSONObject(responseText);
            if (obj.getBoolean("result")) {
                mSessionStarted = true;
            } else {
                mSessionStarted = false;
            }

        } catch (JSONException e) {
            e.printStackTrace(); //report error
        }
    }

    public boolean sessionStarted() {
        return mSessionStarted;
    }
}
