package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 2/2/15.
 */
public class SetSwitchResponse extends Response {

    public SetSwitchResponse(Request request, String responseText) {
        super(request, responseText);
    }
    @Override
    protected void decodeResponseText(String responseText) {
        try {
            JSONObject obj = new JSONObject(responseText);
            if (!obj.getBoolean(RESULT)) {
                setError(obj.getInt(ERROR_CODE), obj.getString(MESSAGE));
            }

        } catch (JSONException e) {
            setError(ErrorCodes.JSON_PARSE_ERROR, e.getMessage());
        }
    }
}
