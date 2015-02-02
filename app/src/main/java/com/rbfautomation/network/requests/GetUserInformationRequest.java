package com.rbfautomation.network.requests;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by brian on 2/2/15.
 */
public class GetUserInformationRequest extends Request {

    public static final int TYPE = 5;
    private static final String API_CALL = "/api/getUserInformation.php";

    public GetUserInformationRequest() {
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

