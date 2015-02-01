package com.rbfautomation.network.requests;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by brian on 2/1/15.
 */
public class GetTokenRequest extends Request {

    public static final int TYPE = 2;

    private String mUsername, mPassword;

    public GetTokenRequest(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username", mUsername));
        nameValuePairs.add(new BasicNameValuePair("password", mPassword));
        return new UrlEncodedFormEntity(nameValuePairs);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getRequestUrl() {
        return "/api/authenticate.php";
    }
}

