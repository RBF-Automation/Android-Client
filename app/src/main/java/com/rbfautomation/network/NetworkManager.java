package com.rbfautomation.network;

import android.content.Context;

import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.network.responses.ResponseFactory;

/**
 * Created by brian on 1/31/15.
 */
public class NetworkManager implements MakeRequest.OnRequsetListener {

    public interface NetworkEventListener {
        void onCompleteRequest(Response response);
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
    public void onResponse(String responseText, Request request) {
        if (mEventHandler != null) {
            mEventHandler.onCompleteRequest(ResponseFactory.createResponse(request, responseText));
        }
    }

}
