package com.rbfautomation.network.responses;

import com.rbfautomation.network.requests.Request;

/**
 * Created by brian on 2/2/15.
 */
public class SetSwitchResponse extends Response {

    public SetSwitchResponse(Request request, String responseText) {
        super(request, responseText);
    }
    @Override
    protected void decodeResponseText(String responseText) {
        //NO EXPECTED RESPONSE
    }
}
