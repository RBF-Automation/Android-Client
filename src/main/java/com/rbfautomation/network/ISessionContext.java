package com.rbfautomation.network;

import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.responses.Response;

/**
 * Created by brian on 2/6/15.
 */
public interface ISessionContext {


    public Request getSessionStartRequest();

    public boolean requestRecoverable(Response response);

    public boolean validateSessionStart(Response response);
}
