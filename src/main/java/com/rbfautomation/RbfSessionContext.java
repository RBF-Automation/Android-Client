package com.rbfautomation;

import com.rbfautomation.network.ISessionContext;
import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.requests.StartSessionRequest;
import com.rbfautomation.network.responses.ErrorCodes;
import com.rbfautomation.network.responses.Response;
import com.rbfautomation.network.responses.StartSessionResponse;

/**
 * Created by brian on 2/6/15.
 */
public class RbfSessionContext implements ISessionContext {

    private final String mAuthToken;

    public RbfSessionContext(String authToken) {
        mAuthToken = authToken;
    }

    @Override
    public Request getSessionStartRequest() {
        return new StartSessionRequest(mAuthToken);
    }

    @Override
    public boolean requestRecoverable(Response response) {
        return (response.hasError() &&
                response.getErrorCode() == ErrorCodes.NOT_LOGGED_IN &&
                response.getType() != StartSessionRequest.TYPE &&
                response.getType() != GetTokenRequest.TYPE);
    }

    @Override
    public boolean validateSessionStart(Response response) {
        return (!response.hasError() && ((StartSessionResponse)response).sessionStarted());
    }
}
