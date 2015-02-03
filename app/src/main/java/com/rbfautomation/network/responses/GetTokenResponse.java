package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by brian on 2/2/15.
 */
public class GetTokenResponse extends Response {

    private String mToken;

    public GetTokenResponse(Request request, String responseText) {
        super(request, responseText);
    }

    @Override
    protected void decodeResponseText(String responseText) {
        try {
            JSONObject obj = new JSONObject(responseText);

            if (obj.getBoolean("result")) {
                mToken = obj.getString("token");
            } else {
                setError(obj.getString("message"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            setError("ERROR parsing JSON");
        }
    }

    public String getToken() {
        return mToken;
    }
}
