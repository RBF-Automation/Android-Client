package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

/**
 * Created by brian on 2/2/15.
 */
public abstract class Response {

    private final Request mRequest;
    private boolean mIsError;
    private String mErrorMessage;

    public Response(Request request, String responseText) {
        mRequest = request;
        mIsError = false;
        decodeResponseText(responseText);
    }

    protected abstract void decodeResponseText(String responseText);

    public Request getRequest() {
        return mRequest;
    }

    public int getType() {
        return mRequest.getType();
    }

    protected void setError(String message) {
        mIsError = true;
        mErrorMessage = message;
    }

    public boolean hasError() {
        return mIsError;
    }

    public String getError() {
        return mErrorMessage;
    }
}
