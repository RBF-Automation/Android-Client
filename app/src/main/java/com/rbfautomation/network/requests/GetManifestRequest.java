package com.rbfautomation.network.requests;

import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 * Created by brian on 1/31/15.
 */
public class GetManifestRequest extends Request {

    public static final int TYPE = 0;
    private static final String API_CALL = "/api/getManifest.php";

    public UrlEncodedFormEntity getRequestParameters() {
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
