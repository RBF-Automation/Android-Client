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
    private static final String API_CALL = "/api/setSwitch.php";
    private static final String ID = "id";
    private static final String STATE = "state";

    private int mRemoteId, mState;

    public SetSwitchRequest(int remoteId, int state) {
        mRemoteId = remoteId;
        mState = state;
    }

    public UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(ID, Integer.toString(mRemoteId)));
        nameValuePairs.add(new BasicNameValuePair(STATE, Integer.toString(mState)));
        return new UrlEncodedFormEntity(nameValuePairs);
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
