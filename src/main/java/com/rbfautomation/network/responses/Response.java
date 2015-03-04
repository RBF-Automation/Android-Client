package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

/**
 * Created by brian on 2/2/15.
 */
public abstract class Response {

    public static final String RESULT = "result";
    public static final String ERROR_CODE = "errorCode";
    public static final String MESSAGE = "message";
    public static final String NO_ROUTE_TO_HOST_ERROR = "No Route to host. Do you have an internet connection?";


    private final Request mRequest;
    private boolean mIsError;
    private int mErrorCode;
    private String mErrorMessage;

    public Response(Request request, String responseText) {
        mRequest = request;
        mIsError = false;
        mErrorCode = -1;

        if (responseText != null) {
            decodeResponseText(responseText);
        } else {
            setError(ErrorCodes.NO_RESPONSE_FROM_SERVER, NO_ROUTE_TO_HOST_ERROR);
        }
    }

    protected abstract void decodeResponseText(String responseText);

    public Request getRequest() {
        return mRequest;
    }

    public int getType() {
        return mRequest.getType();
    }

    protected void setError(int errorCode, String message) {
        mIsError = true;
        mErrorCode = errorCode;
        mErrorMessage = message;
    }

    protected void setError(int errorCode) {
        mIsError = true;
        mErrorCode = errorCode;
        mErrorMessage = "";
    }

    public boolean hasError() {
        return mIsError;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public int getErrorCode() {
        return mErrorCode;
    }
}
