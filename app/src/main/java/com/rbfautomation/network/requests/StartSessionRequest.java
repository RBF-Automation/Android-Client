package com.rbfautomation.network.requests;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public class StartSessionRequest extends Request {

    public static final int TYPE = 3;

    private String mToken;

    public StartSessionRequest(String token) {
        mToken = token;
    }

    public UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("token", mToken));
        return new UrlEncodedFormEntity(nameValuePairs);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getRequestUrl() {
        return "/api/startSession.php";
    }
}
