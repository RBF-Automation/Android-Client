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
        MakeRequest r = new MakeRequest(request, this, mContext); //TEMP
        r.execute();
    }

    @Override
    public void onResponse(String response, Request request) {
        mEventHandler.onCompleteRequest(request, response); //PROCESS JSON HERE
    }

}
