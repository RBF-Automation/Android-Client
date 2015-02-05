package com.rbfautomation.network.responses;

/**
 * Created by brian on 2/2/15.
 */

import com.rbfautomation.network.requests.EndSessionRequest;
import com.rbfautomation.network.requests.GetActivityLogRequest;
import com.rbfautomation.network.requests.GetManifestRequest;
import com.rbfautomation.network.requests.GetTokenRequest;
import com.rbfautomation.network.requests.GetUserInformationRequest;
import com.rbfautomation.network.requests.Request;
import com.rbfautomation.network.requests.SetSwitchRequest;
import com.rbfautomation.network.requests.StartSessionRequest;

public class ResponseFactory {

    public static Response createResponse (Request request, String responseText) {

        switch (request.getType()) {

            case GetUserInformationRequest.TYPE:
                return new GetUserInformationResponse(request, responseText);

            case GetTokenRequest.TYPE:
                return new GetTokenResponse(request, responseText);

            case StartSessionRequest.TYPE:
                return new StartSessionResponse(request, responseText);

            case GetManifestRequest.TYPE:
                return new GetManifestResponse(request, responseText);

            case EndSessionRequest.TYPE:
                return new EndSessionResponse(request, responseText);

            case SetSwitchRequest.TYPE:
                return new SetSwitchResponse(request, responseText);

            case GetActivityLogRequest.TYPE:
                return new GetActivtyListResponse(request, responseText);
        }

        return null;
    }

}
