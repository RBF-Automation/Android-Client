package com.rbfautomation.network;

import android.content.Context;
import android.os.AsyncTask;

import com.rbfautomation.network.requests.Request;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by brian on 1/31/15.
 */
public class MakeRequest extends AsyncTask<String, Void, String> {

    private Context mContext;
    private Request mRequest;
    private OnRequsetListener mEventHandler;


    public MakeRequest(Request request, OnRequsetListener eventHandler, Context context) {
        mContext = context;
        mEventHandler = eventHandler;
        mRequest = request;
    }

    private String startRequest() {
        DefaultHttpClient client = SecureHttpClient.getSecureHttpClient(mContext);

        HttpPost httpPost = new HttpPost(Credentials.HOST + mRequest.getRequestUrl());

        try {
            httpPost.setEntity(mRequest.getRequestParameters());
            HttpResponse result = client.execute(httpPost);
            return EntityUtils.toString(result.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String doInBackground(String... params) {
        return startRequest();
    }

    @Override
    protected void onPostExecute(String result) {
        mEventHandler.onResponse(result, mRequest);
    }


    public interface OnRequsetListener {
        void onResponse(String response, Request request);
    }




}
