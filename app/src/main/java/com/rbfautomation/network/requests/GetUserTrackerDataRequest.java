package com.rbfautomation.network.requests;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by brian on 2/5/15.
 */
public class GetUserTrackerDataRequest extends Request {

    public static final int TYPE = 7;
    private static final String API_CALL = "/api/getIpTrackerData.php";
    private static final String REMOTE_ID = "id";

    private int mRemoteId;

    public GetUserTrackerDataRequest(int remoteId) {
        mRemoteId = remoteId;
    }

    public UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(REMOTE_ID, Integer.toString(mRemoteId)));
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
