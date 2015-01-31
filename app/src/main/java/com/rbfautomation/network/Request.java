package com.rbfautomation.network;

import android.content.Context;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by brian on 1/31/15.
 */
public class Request extends Thread {

    private String mGet;
    private String mPost;
    private Context mContext;
    private OnRequsetListener mEventHandler;

    private Request(String get, String post, Context context, OnRequsetListener eventHandler) {
        mGet = get;
        mPost = post;
        mContext = context;
        mEventHandler = eventHandler;
    }

    @Override
    public void run() {
        startRequest();
    }

    private void startRequest() {
        // Instantiate the custom HttpClient
        DefaultHttpClient client = new SecureHttpClient(mContext);

        HttpPost httpPost = new HttpPost(Credentials.HOST);

        try {
            HttpResponse response = client.execute(httpPost);
            mEventHandler.onResponse(response);
        } catch (IOException e) {
            mEventHandler.onError(e);
        }
    }

    public interface OnRequsetListener {
        void onResponse(HttpResponse response);

        void onError(Exception e);
    }


    public static void makeRequest(String get, String post, Context context, OnRequsetListener eventHandler) {
        (new Request(get, post, context, eventHandler)).start();
    }


}
