package com.rbfautomation.network.requests;

import org.apache.http.client.entity.UrlEncodedFormEntity;

/**
 * Created by brian on 1/31/15.
 */
public class ManifestRequest extends Request {

    public static final int TYPE = 0;

    public UrlEncodedFormEntity getRequestParamaters() {
        return null;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getRequestUrl() {
        return "/index.php";
    }
}
