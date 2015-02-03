package com.rbfautomation.network;

import android.content.Context;

import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.requests.StartSessionRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.network.responses.ResponseFactory;
import com.rbfautomation.network.responses.StartSessionResponse;

/**
 * Created by brian on 1/31/15.
 */
public class NetworkManager implements MakeRequest.OnRequsetListener {

    private static String mCachedToken;

    public interface NetworkEventListener {
        void onCompleteRequest(Response response);
    }

    private NetworkEventListener mEventHandler;
    private Context mContext;

    public NetworkManager(NetworkEventListener eventHandler, Context context) {
        mEventHandler = eventHandler;
        mContext = context;
    }

    public void startSession(String token) {
        mCachedToken = token;
        request(new StartSessionRequest(token));
    }

    public void request(Request request) {
        MakeRequest serverRequest = new MakeRequest(request, this, mContext);
        serverRequest.execute();
    }

    /**
     * Attempts to silently recover a user session if the session has expired.
     * @param originalResponse
     */
    public void recoverRequest(final Response originalResponse) {
        if (mCachedToken != null) {
            MakeRequest serverRequest = new MakeRequest(new StartSessionRequest(mCachedToken), new MakeRequest.OnRequsetListener() {
                public void onResponse(String responseText, Request request) {
                    StartSessionResponse response = (StartSessionResponse) ResponseFactory.createResponse(request, responseText);
                    if (!response.hasError() && response.sessionStarted()) {
                        request(originalResponse.getRequest());
                    } else {
                        handleResponse(originalResponse);
                    }
                }
            }, mContext);
            serverRequest.execute();
        } else {
            handleResponse(originalResponse);
        }
    }


    @Override
    public void onResponse(String responseText, Request request) {
        Response response = ResponseFactory.createResponse(request, responseText);

        if (response.hasError() && response.getErrorCode() == ErrorCodes.NOT_LOGGED_IN) {
            if (request.getType() != StartSessionRequest.TYPE &&
                request.getType() != GetTokenRequest.TYPE) {
                recoverRequest(response);
            }
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
