package com.rbfautomation.network.requests;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by brian on 2/2/15.
 */
public class EndSessionRequest extends Request {

    public static final int TYPE = 4;
    private static final String API_CALL = "/api/endSession.php";

    public EndSessionRequest() {
    }

    public UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getRequestUrl() {
        return API_CALL;
    }
}

