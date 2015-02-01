package com.rbfautomation.network.requests;

import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by brian on 1/31/15.
 */
public abstract class Request {

    public abstract int getType();

    public abstract String getRequestUrl();

    public abstract UrlEncodedFormEntity getRequestParameters() throws UnsupportedEncodingException;
}
