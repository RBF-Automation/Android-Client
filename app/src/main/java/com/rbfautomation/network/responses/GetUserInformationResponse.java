package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 2/2/15.
 */
public class GetUserInformationResponse extends Response {

    private String mUsername;

    public GetUserInformationResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        try {
            JSONObject obj = new JSONObject(responseText);
            if (obj.getBoolean(RESULT)) {
                mUsername = obj.getString("username");
            }

        } catch (JSONException e) {
            e.printStackTrace(); //FIX
            setError(ErrorCodes.JSON_PARSE_ERROR, e.getMessage());
        }
    }

    public String getUsername() {
        return mUsername;
    }
}
