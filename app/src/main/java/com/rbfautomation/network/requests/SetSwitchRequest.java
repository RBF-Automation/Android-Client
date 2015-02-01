package com.rbfautomation.network.requests;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by brian on 1/31/15.
 */
public class SetSwitchRequest extends Request {

    public static final int TYPE = 1;

    private int mRemoteId, mState;

    public SetSwitchRequest(int remoteId, int state) { //add security, probably at network level
        mRemoteId = remoteId;
        mState = state;
    }

    public UrlEncodedFormEntity getRequestParamaters() throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("id", Integer.toString(mRemoteId)));
        nameValuePairs.add(new BasicNameValuePair("state", Integer.toString(mState)));
        return new UrlEncodedFormEntity(nameValuePairs);
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getRequestUrl() {
        return "/api/setSwitch.php";
    }

}
