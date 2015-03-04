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
    private final ISessionContext mSessionContext;

    public NetworkManager(NetworkEventListener eventHandler, Context context, ISessionContext sessionContext) {
        mEventHandler = eventHandler;
        mContext = context;
        mSessionContext = sessionContext;
    }

    public void startSession() {
        request(mSessionContext.getSessionStartRequest());
    }

    public void request(Request request) {
        MakeRequest serverRequest = new MakeRequest(request, this, mContext);
        serverRequest.execute();
    }

    /**
     * Attempts to silently recover a user session if the session has expired.
     * @param originalResponse
     */
    private void recoverRequest(final Response originalResponse) {
        MakeRequest serverRequest = new MakeRequest(mSessionContext.getSessionStartRequest(), new MakeRequest.OnRequsetListener() {
            @Override
            public void onResponse(String responseText, Request request) {
                if (mSessionContext.validateSessionStart(ResponseFactory.createResponse(request, responseText))) {
                    request(originalResponse.getRequest());
                } else {
                    handleResponse(originalResponse);
                }
            }
        }, mContext);
        serverRequest.execute();
    }


    @Override
    public void onResponse(String responseText, Request request) {
        Response response = ResponseFactory.createResponse(request, responseText);
        if (mSessionContext.requestRecoverable(response)) {
            recoverRequest(response);
        } else {
            handleResponse(response);
        }
    }

    public void handleResponse(Response response) {
        if (mEventHandler != null) {
            mEventHandler.onCompleteRequest(response);
        }
    }

}
