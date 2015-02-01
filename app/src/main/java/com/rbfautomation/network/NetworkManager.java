package com.rbfautomation.network;

import android.content.Context;

import com.rbfautomation.network.requests.Request;

/**
 * Created by brian on 1/31/15.
 */
public class NetworkManager implements MakeRequest.OnRequsetListener {

    public interface NetworkEventListener {
        void onCompleteRequest(Request request, String response);
    }

    private NetworkEventListener mEventHandler;
    private Context mContext;

    public NetworkManager(NetworkEventListener eventHandler, Context context) {
        mEventHandler = eventHandler;
        mContext = context;
    }

    public void request(Request request) {
        MakeRequest serverRequest = new MakeRequest(request, this, mContext);
        serverRequest.execute();
    }

    @Override
    public void onResponse(String response, Request request) {
        if (mEventHandler != null) {
            mEventHandler.onCompleteRequest(request, response);
        }
    }

}
