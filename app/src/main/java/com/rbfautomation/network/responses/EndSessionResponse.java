package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

/**
 * Created by brian on 2/2/15.
 */
public class EndSessionResponse extends Response {

    public EndSessionResponse(Request request, String responseText) {
        super(request, responseText);
    }
    @Override
    protected void decodeResponseText(String responseText) {
        //NO EXPECTED RESPONSE
    }
}
